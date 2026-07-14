package com.guciowons.yummify.file.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;

public abstract class FileDomainException extends DomainException {
    public FileDomainException(ErrorMessage errorMessage) {
        super(errorMessage);
    }

    public FileDomainException(ErrorMessage errorMessage, ErrorProperty... properties) {
        super(errorMessage, properties);
    }
}
