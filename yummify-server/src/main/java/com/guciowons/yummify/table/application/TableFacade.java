package com.guciowons.yummify.table.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.table.application.model.*;
import com.guciowons.yummify.table.application.model.mapper.TableCommandMapper;
import com.guciowons.yummify.table.application.port.TableFacadePort;
import com.guciowons.yummify.table.application.usecase.*;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.infrastructure.in.rest.dto.TableOtpDto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class TableFacade implements TableFacadePort {
    private final CreateTableUsecase createTableUsecase;
    private final GetAllTablesUsecase getAllTablesUsecase;
    private final GetTableUsecase getTableUsecase;
    private final UpdateTableUsecase updateTableUsecase;
    private final GenerateTableOtpUsecase generateTableOtpUsecase;
    private final TableCommandMapper tableCommandMapper;

    @Override
    public Table create(UUID restaurantId, String name, int capacity) {
        CreateTableCommand command = tableCommandMapper.toCreateTableCommand(restaurantId, name, capacity);
        return createTableUsecase.create(command);
    }

    @Override
    public List<Table> getAll(UUID restaurantId) {
        GetAllTablesCommand command = tableCommandMapper.toGetAllTablesCommand(restaurantId);
        return getAllTablesUsecase.getAll(command);
    }

    @Override
    public Table getById(UUID id, UUID restaurantId) {
        GetTableCommand command = tableCommandMapper.toGetTableCommand(id, restaurantId);
        return getTableUsecase.get(command);
    }

    @Override
    public Table update(UUID id, UUID restaurantId, String name, int capacity) {
        UpdateTableCommand command = tableCommandMapper.toUpdateTableCommand(id, restaurantId, name, capacity);
        return updateTableUsecase.update(command);
    }

    @Override
    public TableOtpDto generateOtp(UUID id, UUID restaurantId) {
        GenerateTableOtpCommand command = tableCommandMapper.toGenerateTableOtpCommand(id, restaurantId);
        return new TableOtpDto(generateTableOtpUsecase.generate(command), id);
    }
}
