package com.guciowons.yummify.dish;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class IngredientDTO<T> {
    private UUID id;
    private T name;
}
