package com.guciowons.yummify.menu.application.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuErrorMessage implements ErrorMessage {
    MENU_NOT_FOUND_BY_ID("Could not find menu with ID '{{id}}'"),
    MENU_DISHES_NOT_FOUND_BY_ID("Could not find dishes with IDs '{{ids}}' for menu"),
    MENU_SECTION_NOT_FOUND_BY_ID("Could not find menu section with ID '{{id}}'"),
    MENU_ENTRY_NOT_FOUND_BY_ID("Could not find menu entry with ID '{{id}}'");

    private final String message;
}
