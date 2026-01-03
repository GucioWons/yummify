package com.guciowons.yummify.restaurant.application.dto;

import com.guciowons.yummify.common.core.application.dto.BaseEntityDTO;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;

import java.util.UUID;

public record RestaurantClientDTO(
        UUID id,
        String name,
        Language defaultLanguage,
        String description
) implements BaseEntityDTO {
}
