package com.guciowons.yummify.file.domain.port;

import com.guciowons.yummify.file.domain.entity.File;

import java.util.Optional;
import java.util.UUID;

public interface FileRepositoryPort {
    Optional<File> findByIdAndRestaurantId(UUID id, UUID restaurantId);

    File save(File file);

    void delete(File file);
}
