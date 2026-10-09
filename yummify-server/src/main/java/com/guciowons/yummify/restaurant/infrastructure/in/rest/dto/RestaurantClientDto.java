package com.guciowons.yummify.restaurant.infrastructure.in.rest.dto;

import java.util.UUID;

public record RestaurantClientDto(
        UUID id,
        String name,
        String defaultLanguage,
        String currency,
        String description
) {
}
