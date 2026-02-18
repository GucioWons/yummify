package com.guciowons.yummify.file.application.model;

import com.guciowons.yummify.file.domain.entity.File;

public record GetFileUrlCommand(File.Id id, File.RestaurantId restaurantId) {
}
