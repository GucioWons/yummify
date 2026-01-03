package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.DomainError;

public class MenuIsNotActiveException extends DomainException {
    public MenuIsNotActiveException() {
        super(new DomainError(MenuErrorMessage.MENU_IS_NOT_ACTIVE));
    }
}
