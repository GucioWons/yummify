package com.guciowons.yummify.dish;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.dish.dto.IngredientClientDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DishClientDTO extends BaseEntityDTO {
    private String name;
    private String description;
    private List<IngredientClientDTO> ingredients;
}
