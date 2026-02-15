package com.guciowons.yummify.ingredient.domain.repository;

import com.guciowons.yummify.ingredient.domain.entity.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {
    void save(Ingredient ingredient);

    Optional<Ingredient> findByIdAndRestaurantId(Ingredient.Id id, Ingredient.RestaurantId restaurantId);

    List<Ingredient> findAllByRestaurantId(Ingredient.RestaurantId restaurantId);
}
