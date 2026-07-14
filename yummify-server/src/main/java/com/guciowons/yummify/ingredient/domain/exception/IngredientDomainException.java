package com.guciowons.yummify.ingredient.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;

public abstract class IngredientDomainException extends DomainException {
    public IngredientDomainException(ErrorMessage errorMessage, ErrorProperty... properties) {
        super(errorMessage, properties);
    }
}
