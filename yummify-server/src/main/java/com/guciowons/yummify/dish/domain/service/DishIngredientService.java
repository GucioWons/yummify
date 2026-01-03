package com.guciowons.yummify.dish.domain.service;

import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.ingredient.exposed.IngredientExistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DishIngredientService {
    private final IngredientExistencePort ingredientExistencePort;

    public void replaceIngredients(Dish dish, List<UUID> ingredientsIds) {
        if (ingredientsIds.isEmpty()) {
            dish.replaceIngredientIds(Collections.emptyList());
            return;
        }

        ingredientExistencePort.assertAllExist(ingredientsIds, dish.getRestaurantId());
        dish.replaceIngredientIds(ingredientsIds);
    }
}
