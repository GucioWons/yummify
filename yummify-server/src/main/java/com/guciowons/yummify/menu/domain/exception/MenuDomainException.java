package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;

public abstract class MenuDomainException extends DomainException {
    public MenuDomainException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

    public MenuDomainException(ErrorMessage errorMessage, ErrorProperty... properties) {
        super(errorMessage, properties);
    }
}
