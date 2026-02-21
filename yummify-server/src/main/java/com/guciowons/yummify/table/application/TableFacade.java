package com.guciowons.yummify.table.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.exception.infrastructure.DomainExceptionHandler;
import com.guciowons.yummify.table.application.model.*;
import com.guciowons.yummify.table.application.model.mapper.TableCommandMapper;
import com.guciowons.yummify.table.application.usecase.*;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.infrastructure.in.rest.dto.TableOtpDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class TableFacade {
    private final DomainExceptionHandler tableDomainExceptionHandler;
    private final CreateTableUsecase createTableUsecase;
    private final GetAllTablesUsecase getAllTablesUsecase;
    private final GetTableUsecase getTableUsecase;
    private final UpdateTableUsecase updateTableUsecase;
    private final GenerateTableOtpUsecase generateTableOtpUsecase;
    private final TableCommandMapper tableCommandMapper;

    public Table create(UUID restaurantId, String name) {
        CreateTableCommand command = tableCommandMapper.toCreateTableCommand(restaurantId, name);
        return tableDomainExceptionHandler.handle(() -> createTableUsecase.create(command));
    }

    public List<Table> getAll(UUID restaurantId) {
        GetAllTablesCommand command = tableCommandMapper.toGetAllTablesCommand(restaurantId);
        return getAllTablesUsecase.getAll(command);
    }

    public Table getById(UUID id, UUID restaurantId) {
        GetTableCommand command = tableCommandMapper.toGetTableCommand(id, restaurantId);
        return tableDomainExceptionHandler.handle(() -> getTableUsecase.get(command));
    }

    public Table update(UUID id, UUID restaurantId, String name) {
        UpdateTableCommand command = tableCommandMapper.toUpdateTableCommand(id, restaurantId, name);
        return tableDomainExceptionHandler.handle(() -> updateTableUsecase.update(command));
    }

    public TableOtpDTO generateOtp(UUID id, UUID restaurantId) {
        GenerateTableOtpCommand command = tableCommandMapper.toGenerateTableOtpCommand(id, restaurantId);
        return tableDomainExceptionHandler.handle(() -> new TableOtpDTO(generateTableOtpUsecase.generate(command), id));
    }
}
