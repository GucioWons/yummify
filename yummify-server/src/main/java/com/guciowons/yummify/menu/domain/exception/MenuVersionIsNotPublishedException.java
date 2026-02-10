package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;

public class MenuVersionIsNotPublishedException extends DomainException {
    public MenuVersionIsNotPublishedException() {
        super("Menu version is not published");
    }
}
