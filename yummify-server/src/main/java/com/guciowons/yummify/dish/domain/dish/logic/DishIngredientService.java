package com.guciowons.yummify.dish.domain.dish.logic;

import com.guciowons.yummify.dish.domain.ingredient.logic.IngredientGetByIdsService;
import com.guciowons.yummify.dish.domain.dish.entity.Dish;
import com.guciowons.yummify.dish.domain.ingredient.entity.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DishIngredientService {
    private final IngredientGetByIdsService ingredientGetByIdsService;

    public void replaceIngredients(Dish dish, List<UUID> ingredientsIds) {
        dish.clearIngredients();

        if (ingredientsIds == null || ingredientsIds.isEmpty()) {
            return;
        }

        List<Ingredient> ingredients = ingredientGetByIdsService.getByIds(ingredientsIds, dish.getRestaurantId());
        dish.getIngredients().addAll(ingredients);
    }
}
