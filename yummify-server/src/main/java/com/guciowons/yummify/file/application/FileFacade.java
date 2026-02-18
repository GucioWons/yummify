package com.guciowons.yummify.file.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.file.FileFacadePort;
import com.guciowons.yummify.file.application.model.CreateFileCommand;
import com.guciowons.yummify.file.application.model.DeleteFileCommand;
import com.guciowons.yummify.file.application.model.GetFileUrlCommand;
import com.guciowons.yummify.file.application.model.UpdateFileCommand;
import com.guciowons.yummify.file.application.model.mapper.FileCommandMapper;
import com.guciowons.yummify.file.application.usecase.CreateFileUsecase;
import com.guciowons.yummify.file.application.usecase.DeleteFileUsecase;
import com.guciowons.yummify.file.application.usecase.GetFileUrlUsecase;
import com.guciowons.yummify.file.application.usecase.UpdateFileUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class FileFacade implements FileFacadePort {
    private final CreateFileUsecase createFileUsecase;
    private final UpdateFileUsecase updateFileUsecase;
    private final DeleteFileUsecase deleteFileUsecase;
    private final GetFileUrlUsecase getFileUrlUsecase;
    private final DomainExceptionHandler fileDomainExceptionHandler;
    private final FileCommandMapper fileCommandMapper;

    @Override
    public UUID create(String directory, MultipartFile file, UUID restaurantId) {
        return fileDomainExceptionHandler.handle(() -> {
            CreateFileCommand command = fileCommandMapper.toCreateFileCommand(directory, file, restaurantId);
            return createFileUsecase.create(command).getId().value();
        });
    }

    @Override
    public void update(UUID id, String directory, MultipartFile file, UUID restaurantId) {
        fileDomainExceptionHandler.handle(() -> {
            UpdateFileCommand command = fileCommandMapper.toUpdateFileCommand(id, directory, file, restaurantId);
            updateFileUsecase.update(command);
        });
    }

    @Override
    public void delete(UUID id, UUID restaurantId) {
        DeleteFileCommand command = fileCommandMapper.toDeleteFileCommand(id, restaurantId);

        fileDomainExceptionHandler.handle(() -> deleteFileUsecase.delete(command));
    }

    @Override
    public URL getUrl(UUID id, UUID restaurantId) {
        GetFileUrlCommand command = fileCommandMapper.toGetFileUrlCommand(id, restaurantId);

        return fileDomainExceptionHandler.handle(() -> getFileUrlUsecase.getPresignedUrl(command).value());
    }
}
