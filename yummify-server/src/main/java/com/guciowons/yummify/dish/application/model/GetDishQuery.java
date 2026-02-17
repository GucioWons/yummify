package com.guciowons.yummify.dish.application.model;

import com.guciowons.yummify.dish.domain.entity.Dish;

public record GetDishQuery(Dish.Id id, Dish.RestaurantId restaurantId) {
}
