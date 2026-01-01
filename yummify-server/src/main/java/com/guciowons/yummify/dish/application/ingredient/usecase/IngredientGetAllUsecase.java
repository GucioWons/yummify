package com.guciowons.yummify.dish.application.ingredient.usecase;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.dish.domain.ingredient.entity.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class IngredientGetAllUsecase {
    private final RestaurantScopedService<Ingredient> restaurantScopedService;

    public List<Ingredient> getAll() {
        return restaurantScopedService.findAll();
    }
}
