package com.guciowons.yummify.file.application.model;

import com.guciowons.yummify.file.domain.entity.File;

public record DeleteFileCommand(File.Id id, File.RestaurantId restaurantId) {
}
