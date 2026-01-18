package com.guciowons.yummify.file.application.model;

import com.guciowons.yummify.file.domain.entity.value.FileId;
import com.guciowons.yummify.restaurant.RestaurantId;

public record DeleteFileCommand(FileId id, RestaurantId restaurantId) {
}
