package com.softtek.inventory_api.service.impl;

import com.softtek.inventory_api.dto.AssetFilterDTO;
import com.softtek.inventory_api.dto.AssetRequestDTO;
import com.softtek.inventory_api.dto.AssetResponseDTO;
import com.softtek.inventory_api.entity.Asset;
import com.softtek.inventory_api.entity.Category;
import com.softtek.inventory_api.exception.BusinessException;
import com.softtek.inventory_api.repository.AssetRepository;
import com.softtek.inventory_api.repository.CategoryRepository;
import com.softtek.inventory_api.service.AssetService;
import com.softtek.inventory_api.specification.AssetSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public AssetResponseDTO save(AssetRequestDTO dto) {

        //Validar categoría
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() ->
                        new BusinessException(
                                "Categoría no encontrada"
                        ));

        //Validar número de serie único
        if (assetRepository.existsBySerialNumber(dto.serialNumber())) {

            throw new BusinessException(
                    "El número de serie ya existe"
            );
        }

        //Generar UUID
        UUID technicalId = UUID.randomUUID();

        //Generar folio
        String inventoryFolio = generateInventoryFolio(category);

        //Agregar fecha
        LocalDateTime createdAt = LocalDateTime.now();

        Asset asset = Asset.builder()
                .technicalId(technicalId)
                .inventoryFolio(inventoryFolio)
                .serialNumber(dto.serialNumber())
                .brandModel(dto.brandModel())
                .status(dto.status())
                .acquisitionCost(dto.acquisitionCost())
                .createdAt(createdAt)
                .category(category)
                .build();

        Asset savedAsset = assetRepository.save(asset);

        return new AssetResponseDTO(
                savedAsset.getTechnicalId(),
                savedAsset.getInventoryFolio(),
                savedAsset.getSerialNumber(),
                savedAsset.getBrandModel(),
                savedAsset.getStatus(),
                savedAsset.getAcquisitionCost(),
                savedAsset.getCreatedAt(),
                savedAsset.getCategory().getId(),
                savedAsset.getCategory().getName()
        );
    }


    /**
     * Metodo para generar el folio
     *
     * @param category
     * @return
     */
    private String generateInventoryFolio(Category category) {

        long count = assetRepository.countByCategoryId(
                category.getId()
        );

        long next = count + 1;

        int year = LocalDateTime.now().getYear();

        return String.format(
                "%s-%d-%03d",
                category.getPrefixCode(),
                year,
                next
        );
    }

    @Override
    public Page<AssetResponseDTO> findAll(
            AssetFilterDTO filter,
            int page,
            int size,
            String sortBy) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortBy)
                );

        return assetRepository.findAll(
                        AssetSpecification.filter(filter),
                        pageable
                )
                .map(asset -> new AssetResponseDTO(
                        asset.getTechnicalId(),
                        asset.getInventoryFolio(),
                        asset.getSerialNumber(),
                        asset.getBrandModel(),
                        asset.getStatus(),
                        asset.getAcquisitionCost(),
                        asset.getCreatedAt(),
                        asset.getCategory().getId(),
                        asset.getCategory().getName()
                ));
    }
}