package com.guciowons.yummify.file.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.entity.value.FileId;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.domain.port.out.FileRepository;
import com.guciowons.yummify.restaurant.RestaurantId;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class FileLookupService {
    private final FileRepository fileRepository;

    public File getByIdAndRestaurantId(FileId id, RestaurantId restaurantId) throws FileNotFoundException {
        return fileRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new FileNotFoundException(id));
    }
}
