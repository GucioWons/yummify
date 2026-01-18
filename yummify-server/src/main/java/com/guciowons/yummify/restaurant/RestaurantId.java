package com.guciowons.yummify.restaurant;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;

import java.util.UUID;

public record RestaurantId(UUID value) implements IdValueObject {
    public static RestaurantId of(UUID value) {
        return new RestaurantId(value);
    }

    public static RestaurantId random() {
        return of(UUID.randomUUID());
    }
}
