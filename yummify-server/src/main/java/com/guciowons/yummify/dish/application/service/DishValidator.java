package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.exception.DishIngredientsNotFoundException;
import com.guciowons.yummify.ingredient.IngredientExistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@ApplicationService
@RequiredArgsConstructor
public class DishValidator {
    private final IngredientExistencePort ingredientExistencePort;

    public void validate(List<UUID> ingredientIds, Dish.RestaurantId restaurantId) throws DishIngredientsNotFoundException {
        if (ingredientIds.isEmpty()) {
            return;
        }

        List<UUID> missing = ingredientExistencePort.findMissing(ingredientIds, restaurantId.value());

        if (!missing.isEmpty()) {
            throw new DishIngredientsNotFoundException(missing);
        }
    }
}
