package com.guciowons.yummify.file.application.model.mapper;

import com.guciowons.yummify.file.application.model.*;
import com.guciowons.yummify.restaurant.RestaurantId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface FileCommandMapper {
    @Mapping(target = "directory", expression = "java(Directory.of(directory))")
    CreateFileCommand toCreateFileCommand(String directory, FileContent fileContent, RestaurantId restaurantId);

    @Mapping(target = "id", expression = "java(FileId.of(id))")
    @Mapping(target = "directory", expression = "java(Directory.of(directory))")
    UpdateFileCommand toUpdateFileCommand(UUID id, String directory, FileContent fileContent, RestaurantId restaurantId);

    @Mapping(target = "id", expression = "java(FileId.of(id))")
    DeleteFileCommand toDeleteFileCommand(UUID id, RestaurantId restaurantId);

    @Mapping(target = "id", expression = "java(FileId.of(id))")
    GetFileUrlCommand toGetFileUrlCommand(UUID id, RestaurantId restaurantId);
}
