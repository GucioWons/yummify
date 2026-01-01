package com.guciowons.yummify.dish.domain.logic;

import com.guciowons.yummify.dish.infractructure.entity.Dish;
import com.guciowons.yummify.dish.infractructure.entity.Ingredient;
import com.guciowons.yummify.dish.logic.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DishIngredientService {
    private final IngredientService ingredientService;

    public void replaceIngredients(Dish dish, List<UUID> ingredientsIds) {
        dish.clearIngredients();

        if (ingredientsIds == null || ingredientsIds.isEmpty()) {
            return;
        }

        List<Ingredient> ingredients = ingredientService.getEntitiesByIds(ingredientsIds, dish.getRestaurantId());
        dish.getIngredients().addAll(ingredients);
    }
}
