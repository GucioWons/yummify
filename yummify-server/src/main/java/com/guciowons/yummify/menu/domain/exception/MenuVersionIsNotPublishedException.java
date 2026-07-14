package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.menu.domain.exception.message.MenuErrorMessage;

public class MenuVersionIsNotPublishedException extends MenuDomainException {
    public MenuVersionIsNotPublishedException() {
        super(MenuErrorMessage.MENU_VERSION_IS_NOT_PUBLISHED);
    }
}
