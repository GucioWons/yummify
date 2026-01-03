package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MenuErrorMessage implements ErrorMessage {
    MENU_NOT_FOUND_BY_ID("Could not find menu with ID '{{id}}'"),
    NO_ACTIVE_MENU("Could not find active menu"),
    MENU_IS_ACTIVE("Menu is active"),
    MENU_IS_NOT_ACTIVE("Menu is not active");

    private final String message;
}
