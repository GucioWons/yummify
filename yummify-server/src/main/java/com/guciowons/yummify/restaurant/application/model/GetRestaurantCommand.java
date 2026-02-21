package com.guciowons.yummify.restaurant.application.model;

import com.guciowons.yummify.restaurant.domain.entity.Restaurant;

public record GetRestaurantCommand(Restaurant.Id id) {
}
