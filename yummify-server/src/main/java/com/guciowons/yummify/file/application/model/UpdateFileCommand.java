package com.guciowons.yummify.file.application.model;

import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.model.Directory;

public record UpdateFileCommand(File.Id id, Directory directory, FileContent fileContent, File.RestaurantId restaurantId) {
}
