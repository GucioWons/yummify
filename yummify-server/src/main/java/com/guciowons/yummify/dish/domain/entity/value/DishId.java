package com.guciowons.yummify.dish.domain.entity.value;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;

import java.util.UUID;

public record DishId(UUID value) implements IdValueObject {
    public static DishId of(UUID value) {
        return new DishId(value);
    }

    public static DishId random() {
        return of(UUID.randomUUID());
    }
}
