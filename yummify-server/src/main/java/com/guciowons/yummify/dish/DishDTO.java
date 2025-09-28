package com.guciowons.yummify.dish;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DishDTO extends BaseEntityDTO {
    private List<IngredientClientDTO> ingredients;
}
