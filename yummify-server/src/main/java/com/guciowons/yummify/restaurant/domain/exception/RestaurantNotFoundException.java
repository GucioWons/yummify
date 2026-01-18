package com.guciowons.yummify.restaurant.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.restaurant.RestaurantId;
import lombok.Getter;

@Getter
public class RestaurantNotFoundException extends DomainException {
    private final RestaurantId id;

    public RestaurantNotFoundException(RestaurantId id) {
        super("Restaurant not found");
        this.id = id;
    }
}
