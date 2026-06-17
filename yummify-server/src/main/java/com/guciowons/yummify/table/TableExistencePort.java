package com.guciowons.yummify.table;

import org.springframework.modulith.NamedInterface;

import java.util.UUID;

@NamedInterface(name = "TableExistencePort")
public interface TableExistencePort {
    boolean exists(UUID tableId, UUID restaurantId);
}
