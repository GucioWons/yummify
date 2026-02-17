package com.guciowons.yummify.dish.application.model;

import com.guciowons.yummify.dish.domain.entity.Dish;

public record GetAllDishesQuery(Dish.RestaurantId restaurantId) {
}
