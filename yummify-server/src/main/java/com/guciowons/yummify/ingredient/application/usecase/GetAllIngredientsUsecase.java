package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.ingredient.application.model.GetAllIngredientsQuery;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Usecase
@RequiredArgsConstructor
public class GetAllIngredientsUsecase {
    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getAll(GetAllIngredientsQuery query) {
        return ingredientRepository.findAllByRestaurantId(query.restaurantId());
    }
}
