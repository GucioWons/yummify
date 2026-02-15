package com.guciowons.yummify.ingredient.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import lombok.Getter;

@Getter
public class IngredientNotFoundException extends DomainException {
    private final Ingredient.Id id;

    public IngredientNotFoundException(Ingredient.Id id) {
        super("Ingredient not found");
        this.id = id;
    }
}
