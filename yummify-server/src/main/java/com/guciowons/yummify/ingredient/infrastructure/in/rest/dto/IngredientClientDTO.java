package com.guciowons.yummify.ingredient.infrastructure.in.rest.dto;


import java.util.UUID;

public record IngredientClientDTO(
        UUID id,
        String name
) {
}
