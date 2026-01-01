package com.guciowons.yummify.dish.application.ingredient.usecase;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.dish.domain.ingredient.entity.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IngredientGetUsecase {
    private final RestaurantScopedService<Ingredient> restaurantScopedService;

    public Ingredient getById(UUID id) {
        return restaurantScopedService.findById(id);
    }
}
