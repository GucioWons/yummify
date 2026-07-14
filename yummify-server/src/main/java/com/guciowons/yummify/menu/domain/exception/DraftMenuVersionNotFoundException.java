package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.menu.domain.exception.message.MenuErrorMessage;

public class DraftMenuVersionNotFoundException extends MenuDomainException {
    public DraftMenuVersionNotFoundException() {
        super(MenuErrorMessage.DRAFT_MENU_VERSION_NOT_FOUND);
    }
}
