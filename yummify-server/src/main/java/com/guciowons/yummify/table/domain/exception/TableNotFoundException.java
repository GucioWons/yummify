package com.guciowons.yummify.table.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.table.domain.entity.Table;
import lombok.Getter;

@Getter
public class TableNotFoundException extends DomainException {
    private final Table.Id id;

    public TableNotFoundException(Table.Id id) {
        super("Table not found");
        this.id = id;
    }
}
