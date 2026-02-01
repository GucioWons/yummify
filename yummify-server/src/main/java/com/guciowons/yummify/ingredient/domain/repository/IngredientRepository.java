package com.guciowons.yummify.ingredient.domain.repository;

import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.domain.entity.value.IngredientId;
import com.guciowons.yummify.restaurant.RestaurantId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientRepository {
    Ingredient save(Ingredient ingredient);

    Optional<Ingredient> findByIdAndRestaurantId(IngredientId id, RestaurantId restaurantId);

    List<Ingredient> findAllByRestaurantId(RestaurantId restaurantId);

    List<Ingredient> findByIdInAndRestaurantId(List<UUID> ids, RestaurantId restaurantId);
}
