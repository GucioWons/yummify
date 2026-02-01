package com.guciowons.yummify.file.domain.entity.value;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;

import java.util.UUID;

public record FileId(UUID value) implements IdValueObject {
    public static FileId of(UUID value) {
        return new FileId(value);
    }

    public static FileId random() {
        return of(UUID.randomUUID());
    }
}
