package com.guciowons.yummify.dish.application.model;

import com.guciowons.yummify.dish.domain.entity.value.DishId;
import com.guciowons.yummify.restaurant.RestaurantId;

public record GetDishCommand(DishId id, RestaurantId restaurantId) {
}
