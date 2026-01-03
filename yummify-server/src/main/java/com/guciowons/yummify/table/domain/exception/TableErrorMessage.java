package com.guciowons.yummify.table.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TableErrorMessage implements ErrorMessage {
    TABLE_EXISTS_BY_NAME("Table with name '{{name}}' already exists"),
    TABLE_NOT_FOUND_BY_ID("Could not find table with ID '{{id}}'");

    private final String message;
}
