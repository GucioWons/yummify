package com.guciowons.yummify.ingredient.application.dto;

import com.guciowons.yummify.common.core.application.dto.BaseEntityDTO;

import java.util.UUID;

public record IngredientClientDTO(
        UUID id,
        String name
) implements BaseEntityDTO {
}
