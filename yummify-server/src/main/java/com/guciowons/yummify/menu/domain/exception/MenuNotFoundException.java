package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.DomainError;

import java.util.Map;
import java.util.UUID;

public class MenuNotFoundException extends DomainException {
    public MenuNotFoundException(UUID id) {
        super(new DomainError(MenuErrorMessage.MENU_NOT_FOUND_BY_ID, Map.of("id", id)));
    }
}
