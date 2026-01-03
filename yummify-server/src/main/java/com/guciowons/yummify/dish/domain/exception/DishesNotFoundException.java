package com.guciowons.yummify.dish.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.DomainError;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class DishesNotFoundException extends DomainException {
    public DishesNotFoundException(List<UUID> ids) {
        super(new DomainError(
                DishErrorMessage.DISHES_NOT_FOUND_BY_ID,
                Map.of("ids", ids.stream().map(UUID::toString).collect(Collectors.joining(",")))
        ));
    }
}
