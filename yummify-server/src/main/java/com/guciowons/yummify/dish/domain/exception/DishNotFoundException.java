package com.guciowons.yummify.dish.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.DomainError;

import java.util.Map;
import java.util.UUID;

public class DishNotFoundException extends DomainException {
    public DishNotFoundException(UUID id) {
        super(new DomainError(DishErrorMessage.DISH_NOT_FOUND_BY_ID, Map.of("id", id)));
    }
}
