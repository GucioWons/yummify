package com.guciowons.yummify.dish.application.dish.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.dish.application.ingredient.dto.IngredientClientDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;
import java.util.UUID;

public record DishManageDTO(
        UUID id,
        @NotNull TranslatedStringDTO name,
        TranslatedStringDTO description,
        @Valid @NotNull List<IngredientClientDTO> ingredients,
        @Null String imageUrl
) implements BaseEntityDTO {
}
