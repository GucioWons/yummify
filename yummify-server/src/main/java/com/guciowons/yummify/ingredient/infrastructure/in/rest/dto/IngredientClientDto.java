package com.guciowons.yummify.ingredient.infrastructure.in.rest.dto;


import java.util.UUID;

public record IngredientClientDto(
        UUID id,
        String name
) {
}
