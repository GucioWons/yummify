package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.file.application.FileContent;
import com.guciowons.yummify.file.domain.port.FileRepositoryPort;
import com.guciowons.yummify.file.domain.port.FileStoragePort;
import com.guciowons.yummify.file.domain.logic.StorageKeyBuilder;
import com.guciowons.yummify.file.domain.entity.File;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileCreateUsecase {
    private final FileStoragePort fileStoragePort;
    private final FileRepositoryPort fileRepositoryPort;
    private final StorageKeyBuilder storageKeyBuilder;

    @Transactional
    public File create(String directory, FileContent fileContent) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();

        String storageKey = storageKeyBuilder.buildStorageKey(fileContent.originalFilename(), restaurantId, directory);
        fileStoragePort.upload(storageKey, fileContent);

        File entity = new File(restaurantId, storageKey);
        return fileRepositoryPort.save(entity);
    }
}
