package com.guciowons.yummify.ingredient.domain.entity.value;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;

import java.util.UUID;

public record IngredientId(UUID value) implements IdValueObject {
    public static IngredientId of(UUID value) {
        return new IngredientId(value);
    }

    public static IngredientId random() {
        return of(UUID.randomUUID());
    }
}
