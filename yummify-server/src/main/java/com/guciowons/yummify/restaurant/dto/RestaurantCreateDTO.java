package com.guciowons.yummify.restaurant.dto;

import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record RestaurantCreateDTO(
        @NotNull String name,
        @NotNull TranslatedStringDTO description,
        @NotNull Language defaultLanguage,
        @Valid @NotNull UserRequestDTO owner
) {
}
