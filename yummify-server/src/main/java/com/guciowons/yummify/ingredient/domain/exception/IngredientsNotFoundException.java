package com.guciowons.yummify.ingredient.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.DomainError;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class IngredientsNotFoundException extends DomainException {
    public IngredientsNotFoundException(List<UUID> ids) {
        super(new DomainError(
                IngredientErrorMessage.INGREDIENTS_NOT_FOUND_BY_IDS,
                Map.of("ids", ids.stream().map(UUID::toString).collect(Collectors.joining(",")))
        ));
    }
}
