package com.guciowons.yummify.restaurant.infrastructure.in.rest.dto;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.TranslatedStringDTO;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RestaurantManageDTO(
        UUID id,
        @NotNull String name,
        @NotNull String defaultLanguage,
        @NotNull TranslatedStringDTO description
) {
}
