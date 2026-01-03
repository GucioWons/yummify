package com.guciowons.yummify.file.application.service;

import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.domain.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class FileLookupService {
    private final FileRepository fileRepository;

    public File get(UUID id, UUID restaurantId) {
        return fileRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new FileNotFoundException(id));
    }
}
