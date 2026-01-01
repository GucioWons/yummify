package com.guciowons.yummify.dish.domain.ingredient.port;

import com.guciowons.yummify.common.core.repository.RestaurantScopedRepository;
import com.guciowons.yummify.dish.domain.ingredient.entity.Ingredient;

public interface IngredientRepositoryPort extends RestaurantScopedRepository<Ingredient> {
}
