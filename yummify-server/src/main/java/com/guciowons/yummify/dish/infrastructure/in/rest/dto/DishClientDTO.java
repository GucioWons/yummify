package com.guciowons.yummify.dish.infrastructure.in.rest.dto;


import java.util.List;
import java.util.UUID;

public record DishClientDTO(
        UUID id,
        String name,
        String description,
        List<UUID> ingredientIds,
        String imageUrl
) {
}
