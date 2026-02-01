package com.guciowons.yummify.dish.domain.repository;

import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.entity.value.DishId;
import com.guciowons.yummify.restaurant.RestaurantId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DishRepository {
    Dish save(Dish dish);

    Optional<Dish> findByIdAndRestaurantId(DishId id, RestaurantId restaurantId);

    List<Dish> findAllByRestaurantId(RestaurantId restaurantId);

    List<Dish> findByIdInAndRestaurantId(List<UUID> ids, UUID restaurantId);
}
