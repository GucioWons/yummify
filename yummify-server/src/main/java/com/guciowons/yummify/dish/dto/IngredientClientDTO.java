package com.guciowons.yummify.dish.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientClientDTO extends BaseEntityDTO {
    private String name;
}
