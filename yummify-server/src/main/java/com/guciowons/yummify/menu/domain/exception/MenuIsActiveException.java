package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.DomainError;

public class MenuIsActiveException extends DomainException {
    public MenuIsActiveException() {
      super(new DomainError(MenuErrorMessage.MENU_IS_ACTIVE));
    }
}
