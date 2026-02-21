package com.guciowons.yummify.restaurant.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import lombok.Getter;

@Getter
public class RestaurantNotFoundException extends DomainException {
    private final Restaurant.Id id;

    public RestaurantNotFoundException(Restaurant.Id id) {
        super("Restaurant not found");
        this.id = id;
    }
}
