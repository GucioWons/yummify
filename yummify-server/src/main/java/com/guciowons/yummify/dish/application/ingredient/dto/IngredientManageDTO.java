package com.guciowons.yummify.dish.application.ingredient.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record IngredientManageDTO(
        UUID id,
        @NotNull TranslatedStringDTO name
) implements BaseEntityDTO {
}
