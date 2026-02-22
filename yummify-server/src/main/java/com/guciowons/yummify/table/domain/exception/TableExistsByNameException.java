package com.guciowons.yummify.table.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.table.domain.entity.Table;
import lombok.Getter;

@Getter
public class TableExistsByNameException extends DomainException {
    private final Table.Name name;

    public TableExistsByNameException(Table.Name name) {
        super("Table exists by name");
        this.name = name;
    }
}
