package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.file.application.service.FileLookupService;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.model.FileContent;
import com.guciowons.yummify.file.domain.port.FileStoragePort;
import com.guciowons.yummify.file.domain.repository.FileRepository;
import com.guciowons.yummify.file.domain.service.StorageKeyBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileUpdateUsecase {
    private final FileLookupService fileLookupService;
    private final FileRepository fileRepository;
    private final FileStoragePort fileStoragePort;
    private final StorageKeyBuilder storageKeyBuilder;

    @Transactional
    public void update(UUID id, String directory, FileContent fileContent, UUID restaurantId) {
        File file = fileLookupService.get(id, restaurantId);

        String oldStorageKey = file.getStorageKey();
        String newStorageKey = storageKeyBuilder.buildStorageKey(
                fileContent.originalFilename(),
                restaurantId,
                directory
        );

        fileStoragePort.upload(newStorageKey, fileContent);

        try {
            file.setStorageKey(newStorageKey);
            fileRepository.save(file);
            fileStoragePort.delete(oldStorageKey);
        } catch (Exception e) {
            fileStoragePort.delete(newStorageKey);
            throw e;
        }
    }
}
