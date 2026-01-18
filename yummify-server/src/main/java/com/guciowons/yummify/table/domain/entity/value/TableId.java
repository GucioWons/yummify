package com.guciowons.yummify.table.domain.entity.value;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;

import java.util.UUID;

public record TableId(UUID value) implements IdValueObject {
    public static TableId of(UUID value) {
        return new TableId(value);
    }

    public static TableId random() {
        return of(UUID.randomUUID());
    }
}
