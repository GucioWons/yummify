package com.guciowons.yummify.table.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.DomainError;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;

import java.util.Map;

public class TableExistsByNameException extends DomainException {
    public TableExistsByNameException(String name) {
        super(new DomainError(TableErrorMessage.TABLE_EXISTS_BY_NAME, Map.of("name", name)));
    }
}
