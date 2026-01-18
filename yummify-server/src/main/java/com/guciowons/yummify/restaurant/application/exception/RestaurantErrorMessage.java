package com.guciowons.yummify.restaurant.application.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RestaurantErrorMessage implements ErrorMessage {
    RESTAURANT_NOT_FOUND_BY_ID("Could not find restaurant with ID '{{id}}'");

    private final String message;
}
