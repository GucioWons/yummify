package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.DomainError;

public class NoActiveMenuException extends DomainException {
    public NoActiveMenuException() {
        super(new DomainError(MenuErrorMessage.NO_ACTIVE_MENU));
    }
}
