package com.guciowons.yummify.dish.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientManageDTO extends BaseEntityDTO {
    @NotNull
    private TranslatedStringDTO name;
}
