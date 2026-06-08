package com.softtek.inventory_api.dto;

import com.softtek.inventory_api.entity.AssetStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record AssetRequestDTO(
        @NotBlank(message = "El número de serie es obligatorio")
        String serialNumber,

        @NotBlank(message = "La marca/modelo es obligatoria")
        String brandModel,

        @NotNull(message = "El costo es obligatorio")
        @PositiveOrZero(message = "El costo no puede ser negativo")
        BigDecimal acquisitionCost,

        @NotNull(message = "El estado es obligatorio")
        AssetStatus status,

        @NotNull(message = "La categoría es obligatoria")
        Long categoryId
) {
}