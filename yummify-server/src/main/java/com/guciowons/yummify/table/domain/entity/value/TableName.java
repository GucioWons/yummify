package com.guciowons.yummify.table.domain.entity.value;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;

public record TableName(String value) implements ValueObject<String> {
    public static TableName of(String value) {
        return new TableName(value);
    }
}
