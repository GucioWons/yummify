package com.guciowons.yummify.dish;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class DishDTO<T>{
    private UUID id;
    private T name;
    private T description;
    private List<IngredientDTO<String>> ingredients;
}
