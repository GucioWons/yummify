package com.guciowons.yummify.file.application.model;

import com.guciowons.yummify.file.domain.entity.value.FileId;
import com.guciowons.yummify.restaurant.RestaurantId;

public record GetFileUrlCommand(FileId id, RestaurantId restaurantId) {
}
