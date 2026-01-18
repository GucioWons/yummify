package com.guciowons.yummify.file.application.model;

import com.guciowons.yummify.file.domain.entity.value.Directory;
import com.guciowons.yummify.restaurant.RestaurantId;

public record CreateFileCommand(Directory directory, FileContent fileContent, RestaurantId restaurantId) {
}
