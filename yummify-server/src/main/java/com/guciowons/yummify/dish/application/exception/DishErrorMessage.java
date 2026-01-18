package com.guciowons.yummify.dish.application.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DishErrorMessage implements ErrorMessage {
    DISH_NOT_FOUND_BY_ID("Could not find dish with ID '{{id}}'"),
    DISH_INGREDIENTS_NOT_FOUND_BY_IDS("Could not find ingredients with IDs '{{ids}}' for dish");

    private final String message;
}
