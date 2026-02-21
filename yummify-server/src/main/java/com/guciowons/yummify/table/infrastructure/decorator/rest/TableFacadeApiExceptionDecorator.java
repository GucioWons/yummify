package com.guciowons.yummify.table.infrastructure.decorator.rest;

import com.guciowons.yummify.common.exception.infrastructure.in.rest.annotation.HandleDomainExceptions;
import com.guciowons.yummify.table.application.port.TableFacadePort;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.infrastructure.in.rest.dto.TableOtpDTO;
import com.guciowons.yummify.table.infrastructure.in.rest.exception.TableDomainExceptionMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class TableFacadeApiExceptionDecorator implements TableFacadePort {
    private final TableFacadePort delegate;

    @Override
    @HandleDomainExceptions(handler = TableDomainExceptionMapper.class)
    public Table create(UUID restaurantId, String name) {
        return delegate.create(restaurantId, name);
    }

    @Override
    @HandleDomainExceptions(handler = TableDomainExceptionMapper.class)
    public List<Table> getAll(UUID restaurantId) {
        return delegate.getAll(restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = TableDomainExceptionMapper.class)
    public Table getById(UUID id, UUID restaurantId) {
        return delegate.getById(id, restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = TableDomainExceptionMapper.class)
    public Table update(UUID id, UUID restaurantId, String name) {
        return delegate.update(id, restaurantId, name);
    }

    @Override
    @HandleDomainExceptions(handler = TableDomainExceptionMapper.class)
    public TableOtpDTO generateOtp(UUID id, UUID restaurantId) {
        return delegate.generateOtp(id, restaurantId);
    }
}
