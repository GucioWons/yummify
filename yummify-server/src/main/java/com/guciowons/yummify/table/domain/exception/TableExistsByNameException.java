package com.guciowons.yummify.table.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.table.domain.entity.value.TableName;
import lombok.Getter;

@Getter
public class TableExistsByNameException extends DomainException {
    private final TableName name;

    public TableExistsByNameException(TableName name) {
        super("Table exists by name");
        this.name = name;
    }
}
