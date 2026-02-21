package com.guciowons.yummify.ingredient.application.port;

import com.guciowons.yummify.ingredient.domain.entity.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IngredientFacadePort {
    Ingredient create(UUID restaurantId, Map<String, String> name);

    List<Ingredient> getAll(UUID restaurantId);

    Ingredient getById(UUID id, UUID restaurantId);

    Ingredient update(UUID id, UUID restaurantId, Map<String, String> name);
}
