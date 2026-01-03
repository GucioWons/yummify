package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.file.application.service.FileLookupService;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.port.FilePresignedUrlPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileGetUrlUsecase {
    private final FileLookupService fileLookupService;
    private final FilePresignedUrlPort filePresignedUrlPort;

    public String getPresignedUrl(UUID id, UUID restaurantId) {
        File file = fileLookupService.get(id, restaurantId);

        return filePresignedUrlPort.getUrl(file.getStorageKey());
    }
}
