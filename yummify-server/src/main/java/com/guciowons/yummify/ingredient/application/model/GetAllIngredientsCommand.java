package com.guciowons.yummify.ingredient.application.model;

import com.guciowons.yummify.restaurant.RestaurantId;

public record GetAllIngredientsCommand(RestaurantId restaurantId) {
}
