package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.file.application.service.FileLookupService;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.port.FileStoragePort;
import com.guciowons.yummify.file.domain.repository.FileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileDeleteUsecase {
    private final FileLookupService fileLookupService;
    private final FileRepository fileRepository;
    private final FileStoragePort fileStoragePort;

    @Transactional
    public void delete(UUID id, UUID restaurantId) {
        File file = fileLookupService.get(id, restaurantId);

        fileStoragePort.delete(file.getStorageKey());
        fileRepository.delete(file);
    }
}
