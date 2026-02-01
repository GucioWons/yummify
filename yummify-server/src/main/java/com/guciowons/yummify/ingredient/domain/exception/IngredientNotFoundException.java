package com.guciowons.yummify.ingredient.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.ingredient.domain.entity.value.IngredientId;
import lombok.Getter;

@Getter
public class IngredientNotFoundException extends DomainException {
    private final IngredientId id;

    public IngredientNotFoundException(IngredientId id) {
        super("Ingredient not found");
        this.id = id;
    }
}
