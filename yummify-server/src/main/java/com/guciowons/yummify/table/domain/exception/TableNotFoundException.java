package com.guciowons.yummify.table.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.table.domain.entity.value.TableId;
import lombok.Getter;

@Getter
public class TableNotFoundException extends DomainException {
    private final TableId id;

    public TableNotFoundException(TableId id) {
        super("Table not found");
        this.id = id;
    }
}
