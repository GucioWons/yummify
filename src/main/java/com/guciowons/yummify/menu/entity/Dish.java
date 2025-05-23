package com.guciowons.yummify.menu.entity;

import com.guciowons.yummify.ingredient.entity.Ingredient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Dish {
    private UUID id;
    private String name;
    private List<Ingredient> ingredients;
}
