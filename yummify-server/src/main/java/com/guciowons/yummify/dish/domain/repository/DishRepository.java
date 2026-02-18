package com.guciowons.yummify.dish.domain.repository;

import com.guciowons.yummify.dish.domain.entity.Dish;

import java.util.List;
import java.util.Optional;

public interface DishRepository {
    void save(Dish dish);

    Optional<Dish> findByIdAndRestaurantId(Dish.Id id, Dish.RestaurantId restaurantId);

    List<Dish> findAllByRestaurantId(Dish.RestaurantId restaurantId);
}
