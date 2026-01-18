package com.guciowons.yummify.restaurant.infrastructure.in.rest.dto;

import com.guciowons.yummify.common.i8n.domain.enumerated.Language;

import java.util.UUID;

public record RestaurantClientDTO(
        UUID id,
        String name,
        Language defaultLanguage,
        String description
) {
}
