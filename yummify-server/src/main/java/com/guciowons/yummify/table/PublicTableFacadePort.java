package com.guciowons.yummify.table;

import org.springframework.modulith.NamedInterface;

import java.util.UUID;

@NamedInterface(name = "PublicTableFacadePort")
public interface PublicTableFacadePort {
    UUID getTableIdByUserId(UUID userId, UUID restaurantId);
}
