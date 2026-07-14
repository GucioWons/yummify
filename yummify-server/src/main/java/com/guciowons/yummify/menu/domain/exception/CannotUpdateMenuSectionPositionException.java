package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.menu.domain.exception.message.MenuErrorMessage;

public class CannotUpdateMenuSectionPositionException extends MenuDomainException {
    public CannotUpdateMenuSectionPositionException() {
        super(MenuErrorMessage.CANNOT_UPDATE_MENU_SECTION_POSITION);
    }
}
