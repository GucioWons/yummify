package com.guciowons.yummify.file.domain.port.out;

import com.guciowons.yummify.file.domain.entity.value.FileId;
import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.file.domain.entity.File;

import java.util.Optional;

public interface FileRepository {
    File save(File file);

    void delete(File file);

    Optional<File> findByIdAndRestaurantId(FileId id, RestaurantId restaurantId);
}
