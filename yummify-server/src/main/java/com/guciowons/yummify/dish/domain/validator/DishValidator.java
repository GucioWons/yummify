package com.guciowons.yummify.dish.domain.validator;

import com.guciowons.yummify.dish.domain.entity.value.DishIngredientIds;
import com.guciowons.yummify.dish.domain.exception.DishIngredientsNotFoundException;
import com.guciowons.yummify.ingredient.IngredientExistencePort;
import com.guciowons.yummify.restaurant.RestaurantId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DishValidator {
    private final IngredientExistencePort ingredientExistencePort;

    public void validate(DishIngredientIds ingredientIds, RestaurantId restaurantId) throws DishIngredientsNotFoundException {
        if (ingredientIds.value().isEmpty()) {
            return;
        }

        List<UUID> missing = ingredientExistencePort.findMissing(ingredientIds.value(), restaurantId);

        if (!missing.isEmpty()) {
            throw new DishIngredientsNotFoundException(missing);
        }
    }
}
