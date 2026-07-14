package com.guciowons.yummify.ingredient.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.domain.exception.message.IngredientErrorMessage;
import lombok.Getter;

@Getter
public class IngredientNotFoundException extends IngredientDomainException {
    private final Ingredient.Id id;

    public IngredientNotFoundException(Ingredient.Id id) {
        super(IngredientErrorMessage.INGREDIENT_NOT_FOUND_BY_ID, ErrorProperty.of("id", id.value()));
        this.id = id;
    }
}
