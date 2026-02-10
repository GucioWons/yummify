package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;

public class MenuVersionIsNotDraftException extends DomainException {
    public MenuVersionIsNotDraftException() {
        super("Menu version is not draft");
    }
}
