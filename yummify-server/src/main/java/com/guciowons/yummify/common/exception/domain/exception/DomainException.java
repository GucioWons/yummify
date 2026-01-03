package com.guciowons.yummify.common.exception.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.DomainError;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final DomainError domainError;

    public DomainException(DomainError error) {
        this.domainError = error;
    }
}
