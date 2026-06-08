package com.softtek.inventory_api.dto;

import com.softtek.inventory_api.entity.AssetStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AssetResponseDTO(
        UUID technicalId,

        String inventoryFolio,

        String serialNumber,

        String brandModel,

        AssetStatus status,

        BigDecimal acquisitionCost,

        LocalDateTime createdAt,

        Long categoryId,

        String categoryName
) {
}
