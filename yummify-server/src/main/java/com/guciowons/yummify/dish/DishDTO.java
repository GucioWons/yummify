package com.guciowons.yummify.dish;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DishDTO{
    private UUID id;
    private List<IngredientClientDTO> ingredients;
}
