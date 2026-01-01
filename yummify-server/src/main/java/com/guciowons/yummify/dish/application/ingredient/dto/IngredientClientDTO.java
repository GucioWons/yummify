package com.guciowons.yummify.dish.application.ingredient.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;

import java.util.UUID;

public record IngredientClientDTO(
        UUID id,
        String name
) implements BaseEntityDTO {
}
