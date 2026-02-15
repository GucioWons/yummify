package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.ingredient.application.model.GetIngredientQuery;
import com.guciowons.yummify.ingredient.application.service.IngredientLookupService;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GetIngredientUsecase {
    private final IngredientLookupService ingredientLookupService;

    public Ingredient getById(GetIngredientQuery query) {
        return ingredientLookupService.getByIdAndRestaurantId(query.id(), query.restaurantId());
    }
}
