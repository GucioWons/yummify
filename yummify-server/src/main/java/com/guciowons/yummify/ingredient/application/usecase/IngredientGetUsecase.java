package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.ingredient.application.model.GetIngredientCommand;
import com.guciowons.yummify.ingredient.application.service.IngredientLookupService;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.domain.exception.IngredientNotFoundException;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class IngredientGetUsecase {
    private final IngredientLookupService ingredientLookupService;

    public Ingredient getById(GetIngredientCommand command) throws IngredientNotFoundException {
        return ingredientLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());
    }
}
