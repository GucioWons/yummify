package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.file.application.model.CreateFileCommand;
import com.guciowons.yummify.file.application.port.out.FileStoragePort;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.port.out.FileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateFileUsecase {
    private final FileRepository fileRepository;
    private final FileStoragePort fileStoragePort;

    @Transactional
    public File create(CreateFileCommand command) {
        File file = File.create(
                command.restaurantId(),
                File.StorageKey.of(command.fileContent().filename(), command.restaurantId(), command.directory())
        );

        fileStoragePort.store(file.getStorageKey(), command.fileContent());

        fileRepository.save(file);

        return file;
    }
}
