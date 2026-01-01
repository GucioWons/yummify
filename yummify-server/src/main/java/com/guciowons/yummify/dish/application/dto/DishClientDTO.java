package com.guciowons.yummify.dish.application.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;

import java.util.List;
import java.util.UUID;

public record DishClientDTO(
        UUID id,
        String name,
        String description,
        List<IngredientClientDTO> ingredients,
        String imageUrl
) implements BaseEntityDTO {
}
