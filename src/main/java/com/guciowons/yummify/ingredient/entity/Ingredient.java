package com.guciowons.yummify.ingredient.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Ingredient {
    private UUID id;
    private String name;
    private UUID restaurantId;
}
