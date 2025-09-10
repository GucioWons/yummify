package com.guciowons.yummify.dish.data;

import com.guciowons.yummify.common.temp.repository.RestaurantScopedRepository;
import com.guciowons.yummify.dish.entity.Ingredient;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends RestaurantScopedRepository<Ingredient> {
}
