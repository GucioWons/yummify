package com.guciowons.yummify.restaurant.domain.entity.value;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;

public record RestaurantName(String value) implements ValueObject<String> {
    public static RestaurantName of(String value) {
        return new RestaurantName(value);
    }
}
