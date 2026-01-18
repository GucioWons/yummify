package com.guciowons.yummify.table.application.model.mapper;

import com.guciowons.yummify.table.application.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TableCommandMapper {
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    @Mapping(target = "name", expression = "java(TableName.of(name))")
    CreateTableCommand toCreateTableCommand(UUID restaurantId, String name);

    @Mapping(target = "id", expression = "java(TableId.of(id))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    GenerateTableOtpCommand toGenerateTableOtpCommand(UUID id, UUID restaurantId);

    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    GetAllTablesCommand toGetAllTablesCommand(UUID restaurantId);

    @Mapping(target = "id", expression = "java(TableId.of(id))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    GetTableCommand toGetTableCommand(UUID id, UUID restaurantId);

    @Mapping(target = "id", expression = "java(TableId.of(id))")
    @Mapping(target = "restaurantId", expression = "java(RestaurantId.of(restaurantId))")
    @Mapping(target = "name", expression = "java(TableName.of(name))")
    UpdateTableCommand toUpdateTableCommand(UUID id, UUID restaurantId, String name);
}
