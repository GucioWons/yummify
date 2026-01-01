package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.file.domain.port.FilePresignedUrlPort;
import com.guciowons.yummify.file.domain.port.FileRepositoryPort;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.domain.entity.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileGetUrlUsecase {
    private final FilePresignedUrlPort filePresignedUrlPort;
    private final FileRepositoryPort fileRepositoryPort;

    public String getPresignedUrl(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        File file = fileRepositoryPort.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new FileNotFoundException(id));

        return filePresignedUrlPort.getPresignedUrl(file.getStorageKey());
    }
}
