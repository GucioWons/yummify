package com.guciowons.yummify.ingredient.application.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IngredientErrorMessage implements ErrorMessage {
    INGREDIENT_NOT_FOUND_BY_ID("Could not find ingredient with ID '{{id}}'");

    private final String message;
}
