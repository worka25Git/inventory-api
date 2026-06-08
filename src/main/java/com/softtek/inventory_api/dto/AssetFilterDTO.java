package com.softtek.inventory_api.dto;

import com.softtek.inventory_api.entity.AssetStatus;

import java.math.BigDecimal;

public record AssetFilterDTO(
        String serialNumber,
        String brandModel,
        Long categoryId,
        AssetStatus status,
        BigDecimal costMin,
        BigDecimal costMax
) {
}
