package com.guciowons.yummify.file.application.model.mapper;

import com.guciowons.yummify.file.application.model.*;
import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.exception.CannotGetFileException;
import com.guciowons.yummify.file.domain.model.Directory;
import com.guciowons.yummify.file.domain.model.Filename;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface FileCommandMapper {
    @Mapping(target = "fileContent", source = "file")
    CreateFileCommand toCreateFileCommand(String directory, MultipartFile file, UUID restaurantId);

    @Mapping(target = "fileContent", source = "file")
    UpdateFileCommand toUpdateFileCommand(UUID id, String directory, MultipartFile file, UUID restaurantId);

    DeleteFileCommand toDeleteFileCommand(UUID id, UUID restaurantId);

    GetFileUrlCommand toGetFileUrlCommand(UUID id, UUID restaurantId);

    default File.Id toId(UUID id) {
        return File.Id.of(id);
    }

    default File.RestaurantId toRestaurantId(UUID id) {
        return File.RestaurantId.of(id);
    }

    default Directory toDirectory(String directory) {
        return Directory.of(directory);
    }

    default FileContent toFileContent(MultipartFile file) throws CannotGetFileException {
        try {
            return new FileContent(
                    Filename.of(file.getOriginalFilename()),
                    file.getContentType(),
                    file.getInputStream(),
                    file.getSize()
            );
        } catch (IOException e) {
            throw new CannotGetFileException();
        }
    }
}
