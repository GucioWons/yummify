package com.guciowons.yummify.dish.application.model;

import com.guciowons.yummify.dish.domain.entity.value.DishId;
import com.guciowons.yummify.restaurant.RestaurantId;
import org.springframework.web.multipart.MultipartFile;

public record UpdateDishImageCommand(DishId id, MultipartFile image, RestaurantId restaurantId) {
}
