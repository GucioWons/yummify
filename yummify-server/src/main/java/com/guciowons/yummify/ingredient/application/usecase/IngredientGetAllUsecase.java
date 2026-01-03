package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class IngredientGetAllUsecase {
    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getAll(UUID restaurantId) {
        return ingredientRepository.findAllByRestaurantId(restaurantId);
    }
}
