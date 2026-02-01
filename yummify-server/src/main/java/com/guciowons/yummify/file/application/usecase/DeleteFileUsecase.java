package com.guciowons.yummify.file.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.file.application.service.FileLookupService;
import com.guciowons.yummify.file.application.model.DeleteFileCommand;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.exception.FileNotFoundException;
import com.guciowons.yummify.file.application.port.out.FileStoragePort;
import com.guciowons.yummify.file.domain.port.out.FileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class DeleteFileUsecase {
    private final FileLookupService fileLookupService;
    private final FileRepository fileRepository;
    private final FileStoragePort fileStoragePort;

    @Transactional
    public void delete(DeleteFileCommand command) throws FileNotFoundException {
        File file = fileLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());

        fileStoragePort.remove(file.getStorageKey());
        fileRepository.delete(file);
    }
}
