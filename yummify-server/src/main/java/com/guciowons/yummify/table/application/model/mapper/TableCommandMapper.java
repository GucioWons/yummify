package com.guciowons.yummify.table.application.model.mapper;

import com.guciowons.yummify.table.application.model.*;
import com.guciowons.yummify.table.domain.entity.Table;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TableCommandMapper {
    CreateTableCommand toCreateTableCommand(UUID restaurantId, String name);

    GenerateTableOtpCommand toGenerateTableOtpCommand(UUID id, UUID restaurantId);

    GetAllTablesCommand toGetAllTablesCommand(UUID restaurantId);

    GetTableCommand toGetTableCommand(UUID id, UUID restaurantId);

    UpdateTableCommand toUpdateTableCommand(UUID id, UUID restaurantId, String name);

    default Table.Id toId(UUID id) {
        return Table.Id.of(id);
    }

    default Table.RestaurantId toRestaurantId(UUID id) {
        return Table.RestaurantId.of(id);
    }

    default Table.Name toName(String name) {
        return Table.Name.of(name);
    }
}
