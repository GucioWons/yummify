package com.guciowons.yummify.table.application.port;

import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.infrastructure.in.rest.dto.TableOtpDto;

import java.util.List;
import java.util.UUID;

public interface TableFacadePort {
    Table create(UUID restaurantId, String name, int capacity);

    List<Table> getAll(UUID restaurantId);

    Table getById(UUID id, UUID restaurantId);

    Table update(UUID id, UUID restaurantId, String name, int capacity);

    TableOtpDto generateOtp(UUID id, UUID restaurantId);
}
