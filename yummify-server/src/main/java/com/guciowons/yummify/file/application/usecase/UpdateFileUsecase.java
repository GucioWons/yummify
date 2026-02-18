package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.file.application.model.UpdateFileCommand;
import com.guciowons.yummify.file.application.port.out.FileStoragePort;
import com.guciowons.yummify.file.application.service.FileLookupService;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.domain.port.out.FileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class UpdateFileUsecase {
    private final FileLookupService fileLookupService;
    private final FileRepository fileRepository;
    private final FileStoragePort fileStoragePort;

    @Transactional
    public void update(UpdateFileCommand command) throws FileNotFoundException {
        File file = fileLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());

        File.StorageKey oldStorageKey = file.getStorageKey();
        File.StorageKey newStorageKey = File.StorageKey.of(
                command.fileContent().filename(),
                command.restaurantId(),
                command.directory()
        );

        fileStoragePort.store(newStorageKey, command.fileContent());

        try {
            file.changeStorageKey(newStorageKey);
            fileRepository.save(file);
            fileStoragePort.remove(oldStorageKey);
        } catch (Exception e) {
            fileStoragePort.remove(newStorageKey);
            throw e;
        }
    }
}
