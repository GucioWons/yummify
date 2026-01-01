package com.guciowons.yummify.dish.infractructure.data;

import com.guciowons.yummify.common.core.repository.RestaurantScopedRepository;
import com.guciowons.yummify.dish.infractructure.entity.Ingredient;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends RestaurantScopedRepository<Ingredient> {
}
