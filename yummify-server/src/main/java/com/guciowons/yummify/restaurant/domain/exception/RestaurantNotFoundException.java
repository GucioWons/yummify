package com.guciowons.yummify.restaurant.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.domain.exception.message.RestaurantErrorMessage;
import lombok.Getter;

@Getter
public class RestaurantNotFoundException extends RestaurantDomainException {
    private final Restaurant.Id id;

    public RestaurantNotFoundException(Restaurant.Id id) {
        super(RestaurantErrorMessage.RESTAURANT_NOT_FOUND_BY_ID, ErrorProperty.of("id", id.value()));
        this.id = id;
    }
}
