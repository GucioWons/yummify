package com.guciowons.yummify.table.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.DomainError;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;

import java.util.Map;
import java.util.UUID;

public class TableNotFoundException extends DomainException {
  public TableNotFoundException(UUID id) {
    super(new DomainError(TableErrorMessage.TABLE_NOT_FOUND_BY_ID, Map.of("id", id))
    );
  }
}
