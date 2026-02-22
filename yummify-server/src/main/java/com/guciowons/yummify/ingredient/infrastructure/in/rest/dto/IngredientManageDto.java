package com.guciowons.yummify.ingredient.infrastructure.in.rest.dto;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.TranslatedStringDto;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record IngredientManageDto(
        UUID id,
        @NotNull TranslatedStringDto name
) {
}
