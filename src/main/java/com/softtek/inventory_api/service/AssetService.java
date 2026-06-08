package com.softtek.inventory_api.service;

import com.softtek.inventory_api.dto.AssetFilterDTO;
import com.softtek.inventory_api.dto.AssetRequestDTO;
import com.softtek.inventory_api.dto.AssetResponseDTO;
import org.springframework.data.domain.Page;

public interface AssetService {

    AssetResponseDTO save(AssetRequestDTO dto);

    Page<AssetResponseDTO> findAll(
            AssetFilterDTO filter,
            int page,
            int size,
            String sortBy
    );

}
