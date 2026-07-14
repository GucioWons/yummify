package com.guciowons.yummify.auth.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;

public abstract class AuthDomainException extends DomainException {
    public AuthDomainException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

    public AuthDomainException(ErrorMessage errorMessage, ErrorProperty... properties) {
        super(errorMessage, properties);
    }
}
