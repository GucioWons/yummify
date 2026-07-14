package com.guciowons.yummify.dish.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;

public abstract class DishDomainException extends DomainException {
    public DishDomainException(ErrorMessage errorMessage, ErrorProperty... properties) {
        super(errorMessage, properties);
    }
}
