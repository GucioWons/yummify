package com.guciowons.yummify.common.file.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;

public abstract class FileDomainException extends DomainException {
    public FileDomainException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}
