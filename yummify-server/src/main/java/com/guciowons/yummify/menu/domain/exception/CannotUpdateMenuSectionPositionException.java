package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;

public class CannotUpdateMenuSectionPositionException extends DomainException {
    public CannotUpdateMenuSectionPositionException() {
        super("Cannot update menu section position");
    }
}
