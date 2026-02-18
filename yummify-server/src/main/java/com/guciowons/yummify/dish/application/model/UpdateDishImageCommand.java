package com.guciowons.yummify.dish.application.model;

import com.guciowons.yummify.dish.domain.entity.Dish;
import org.springframework.web.multipart.MultipartFile;

public record UpdateDishImageCommand(Dish.Id id, MultipartFile image, Dish.RestaurantId restaurantId) {
}
