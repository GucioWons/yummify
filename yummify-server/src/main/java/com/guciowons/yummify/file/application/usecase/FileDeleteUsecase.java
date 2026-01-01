package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.file.domain.port.FileRepositoryPort;
import com.guciowons.yummify.file.domain.port.FileStoragePort;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.domain.entity.File;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FileDeleteUsecase {
    private final FileStoragePort fileStoragePort;
    private final FileRepositoryPort fileRepositoryPort;

    @Transactional
    public void delete(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();

        File file = fileRepositoryPort.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(()-> new FileNotFoundException(id));

        fileStoragePort.delete(file.getStorageKey());
        fileRepositoryPort.delete(file);
    }
}
