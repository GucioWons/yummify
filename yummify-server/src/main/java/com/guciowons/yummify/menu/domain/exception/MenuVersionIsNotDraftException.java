package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.menu.domain.exception.message.MenuErrorMessage;

public class MenuVersionIsNotDraftException extends MenuDomainException {
    public MenuVersionIsNotDraftException() {
        super(MenuErrorMessage.MENU_VERSION_IS_NOT_DRAFT);
    }
}
