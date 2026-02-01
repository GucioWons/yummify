package com.guciowons.yummify.dish.infrastructure.in.rest.dto;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.TranslatedStringDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;
import java.util.UUID;

public record DishManageDTO(
        UUID id,
        @NotNull TranslatedStringDTO name,
        TranslatedStringDTO description,
        @Valid @NotNull List<UUID> ingredientIds,
        @Null String imageUrl
) {
}
