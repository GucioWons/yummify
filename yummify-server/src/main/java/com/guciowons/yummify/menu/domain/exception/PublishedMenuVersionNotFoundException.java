package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.menu.domain.exception.message.MenuErrorMessage;

public class PublishedMenuVersionNotFoundException extends MenuDomainException {
    public PublishedMenuVersionNotFoundException() {
        super(MenuErrorMessage.PUBLISHED_MENU_VERSION_NOT_FOUND);
    }
}
