package com.guciowons.yummify.dish.domain.entity.value;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;

import java.util.List;
import java.util.UUID;

public record DishIngredientIds(List<UUID> value) implements ValueObject<List<UUID>> {
    public static DishIngredientIds of(List<UUID> value) {
        return new DishIngredientIds(value);
    }
}
