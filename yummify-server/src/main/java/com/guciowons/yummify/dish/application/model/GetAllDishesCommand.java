package com.guciowons.yummify.dish.application.model;

import com.guciowons.yummify.restaurant.RestaurantId;

public record GetAllDishesCommand(RestaurantId restaurantId) {
}
