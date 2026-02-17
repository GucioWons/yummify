package com.guciowons.yummify.file.application.model.mapper;

import com.guciowons.yummify.file.application.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface FileCommandMapper {
    @Mapping(target = "directory", expression = "java(Directory.of(directory))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    CreateFileCommand toCreateFileCommand(String directory, FileContent fileContent, UUID restaurantId);

    @Mapping(target = "id", expression = "java(FileId.of(id))")
    @Mapping(target = "directory", expression = "java(Directory.of(directory))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    UpdateFileCommand toUpdateFileCommand(UUID id, String directory, FileContent fileContent, UUID restaurantId);

    @Mapping(target = "id", expression = "java(FileId.of(id))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    DeleteFileCommand toDeleteFileCommand(UUID id, UUID restaurantId);

    @Mapping(target = "id", expression = "java(FileId.of(id))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    GetFileUrlCommand toGetFileUrlCommand(UUID id, UUID restaurantId);
}
