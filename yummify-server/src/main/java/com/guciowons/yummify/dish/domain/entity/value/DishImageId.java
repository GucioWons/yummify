package com.guciowons.yummify.dish.domain.entity.value;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;

import java.util.UUID;

public record DishImageId(UUID value) implements ValueObject<UUID> {
    public static DishImageId of(UUID value) {
        return new DishImageId(value);
    }
}
