package com.guciowons.yummify.dish.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DishErrorMessage implements ErrorMessage {
    DISH_NOT_FOUND_BY_ID("Could not find dish with ID '{{id}}'"),
    DISHES_NOT_FOUND_BY_ID("Could not find dishes with IDs '{{ids}}'");

    private final String message;
}
