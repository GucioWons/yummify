package com.guciowons.yummify.file.domain.port.out;

import com.guciowons.yummify.file.domain.entity.File;

import java.util.Optional;

public interface FileRepository {
    void save(File file);

    void delete(File file);

    Optional<File> findByIdAndRestaurantId(File.Id id, File.RestaurantId restaurantId);
}
