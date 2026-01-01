package com.guciowons.yummify.dish.domain.dish.port;

import com.guciowons.yummify.common.core.repository.RestaurantScopedRepository;
import com.guciowons.yummify.dish.domain.dish.entity.Dish;

public interface DishRepositoryPort extends RestaurantScopedRepository<Dish> {
}
