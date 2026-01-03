package com.guciowons.yummify.ingredient.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.DomainError;

import java.util.Map;
import java.util.UUID;

public class IngredientNotFoundException extends DomainException {
  public IngredientNotFoundException(UUID id) {
    super(new DomainError(IngredientErrorMessage.INGREDIENT_NOT_FOUND_BY_ID, Map.of("id", id)));
  }
}
