package com.guciowons.yummify.dish.application.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class IngredientManageDTO implements BaseEntityDTO {
    private UUID id;
    @NotNull
    private TranslatedStringDTO name;
}
