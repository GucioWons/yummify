package com.guciowons.yummify.table.domain.entity.value;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;

import java.util.UUID;

public record TableUserId(UUID value) implements ValueObject<UUID> {
    public static TableUserId of(UUID value) {
        return new TableUserId(value);
    }
}
