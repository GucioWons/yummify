package com.guciowons.yummify.file.application.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileErrorMessage implements ErrorMessage {
    FILE_NOT_FOUND_EXCEPTION("Could not find file with ID '{{id}}'"),
    CANNOT_GET_FILE("Could not get file"),
    STORAGE_KEY_IS_BLANK("StorageKey cannot be blank"),
    STORAGE_KEY_STARTS_WITH_SLASH("StorageKey cannot start with /"),
    STORAGE_KEY_CONTAINS_DOTS("StorageKey cannot contain '..'");

    private final String message;
}
