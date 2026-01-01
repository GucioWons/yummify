package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.file.application.FileContent;
import com.guciowons.yummify.file.domain.port.FileRepositoryPort;
import com.guciowons.yummify.file.domain.port.FileStoragePort;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.domain.logic.StorageKeyBuilder;
import com.guciowons.yummify.file.domain.entity.File;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileUpdateUsecase {
    private final FileStoragePort fileStoragePort;
    private final FileRepositoryPort fileRepositoryPort;
    private final StorageKeyBuilder storageKeyBuilder;

    @Transactional
    public void update(UUID id, String directory, FileContent fileContent) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        File fileEntity = fileRepositoryPort.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new FileNotFoundException(id));

        String oldStorageKey = fileEntity.getStorageKey();
        String newStorageKey = storageKeyBuilder.buildStorageKey(fileContent.originalFilename(), restaurantId, directory);

        fileStoragePort.upload(newStorageKey, fileContent);

        try {
            fileEntity.setStorageKey(newStorageKey);
            fileRepositoryPort.save(fileEntity);
            fileStoragePort.delete(oldStorageKey);
        } catch (Exception e) {
            fileStoragePort.delete(newStorageKey);
            throw e;
        }
    }
}
