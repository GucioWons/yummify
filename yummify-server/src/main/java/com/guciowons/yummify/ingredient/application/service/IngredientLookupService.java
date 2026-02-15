package com.guciowons.yummify.ingredient.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.domain.exception.IngredientNotFoundException;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class IngredientLookupService {
    private final IngredientRepository ingredientRepository;

    public Ingredient getByIdAndRestaurantId(Ingredient.Id id, Ingredient.RestaurantId restaurantId) {
        return ingredientRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }
}
