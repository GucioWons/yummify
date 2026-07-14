package com.guciowons.yummify.table.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;

public abstract class TableDomainException extends DomainException {
    public TableDomainException(ErrorMessage errorMessage, ErrorProperty... properties) {
        super(errorMessage, properties);
    }
}
