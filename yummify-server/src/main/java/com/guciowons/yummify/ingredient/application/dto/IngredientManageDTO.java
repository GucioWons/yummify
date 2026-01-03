package com.guciowons.yummify.ingredient.application.dto;

import com.guciowons.yummify.common.core.application.dto.BaseEntityDTO;
import com.guciowons.yummify.common.i8n.application.dto.TranslatedStringDTO;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record IngredientManageDTO(
        UUID id,
        @NotNull TranslatedStringDTO name
) implements BaseEntityDTO {
}
