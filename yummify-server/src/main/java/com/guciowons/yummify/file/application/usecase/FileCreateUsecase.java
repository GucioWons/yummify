package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.file.domain.model.FileContent;
import com.guciowons.yummify.file.domain.port.FileStoragePort;
import com.guciowons.yummify.file.domain.repository.FileRepository;
import com.guciowons.yummify.file.domain.service.StorageKeyBuilder;
import com.guciowons.yummify.file.domain.entity.File;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileCreateUsecase {
    private final FileRepository fileRepository;
    private final FileStoragePort fileStoragePort;
    private final StorageKeyBuilder storageKeyBuilder;

    @Transactional
    public File create(String directory, FileContent fileContent, UUID restaurantId) {
        String storageKey = storageKeyBuilder.buildStorageKey(fileContent.originalFilename(), restaurantId, directory);
        fileStoragePort.upload(storageKey, fileContent);

        File entity = new File(restaurantId, storageKey);
        return fileRepository.save(entity);
    }
}
