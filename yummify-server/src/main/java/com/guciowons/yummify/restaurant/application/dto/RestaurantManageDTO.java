package com.guciowons.yummify.restaurant.application.dto;

import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.common.i8n.application.dto.TranslatedStringDTO;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RestaurantManageDTO(
        UUID id,
        @NotNull String name,
        @NotNull Language defaultLanguage,
        @NotNull TranslatedStringDTO description
) {
}
