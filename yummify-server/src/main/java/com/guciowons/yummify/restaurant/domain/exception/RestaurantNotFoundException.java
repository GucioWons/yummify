package com.guciowons.yummify.restaurant.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.DomainError;

import java.util.Map;
import java.util.UUID;

public class RestaurantNotFoundException extends DomainException {
    public RestaurantNotFoundException(UUID id) {
        super(new DomainError(RestaurantErrorMessage.RESTAURANT_NOT_FOUND_BY_ID, Map.of("id", id)));
    }
}
