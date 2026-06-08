package com.softtek.inventory_api.controller;

import com.softtek.inventory_api.dto.AssetFilterDTO;
import com.softtek.inventory_api.dto.AssetRequestDTO;
import com.softtek.inventory_api.dto.AssetResponseDTO;
import com.softtek.inventory_api.entity.AssetStatus;
import com.softtek.inventory_api.service.AssetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    public ResponseEntity<AssetResponseDTO> save(@RequestBody @Valid AssetRequestDTO dto) {
        AssetResponseDTO response = assetService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<Page<AssetResponseDTO>> findAll(
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) String brandModel,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) AssetStatus status,
            @RequestParam(required = false) BigDecimal costMin,
            @RequestParam(required = false) BigDecimal costMax,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy
    ) {

        AssetFilterDTO filter = new AssetFilterDTO(
                        serialNumber,
                        brandModel,
                        categoryId,
                        status,
                        costMin,
                        costMax
                );

        return ResponseEntity.ok(
                assetService.findAll(
                        filter,
                        page,
                        size,
                        sortBy
                )
        );
    }
}
