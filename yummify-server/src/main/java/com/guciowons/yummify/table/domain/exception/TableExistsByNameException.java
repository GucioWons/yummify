package com.guciowons.yummify.table.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.exception.message.TableErrorMessage;
import lombok.Getter;

@Getter
public class TableExistsByNameException extends TableDomainException {
    private final Table.Name name;

    public TableExistsByNameException(Table.Name name) {
        super(TableErrorMessage.TABLE_EXISTS_BY_NAME, ErrorProperty.of("name", name.value()));
        this.name = name;
    }
}
