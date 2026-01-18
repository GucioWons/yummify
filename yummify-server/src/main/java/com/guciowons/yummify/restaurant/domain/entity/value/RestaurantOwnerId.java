package com.guciowons.yummify.restaurant.domain.entity.value;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;

import java.util.UUID;

public record RestaurantOwnerId(UUID value) implements ValueObject<UUID> {
    public static RestaurantOwnerId of(UUID value) {
        return new RestaurantOwnerId(value);
    }
}
