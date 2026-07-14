package com.guciowons.yummify.table.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.exception.message.TableErrorMessage;
import lombok.Getter;

@Getter
public class TableNotFoundException extends TableDomainException {
    private final Table.Id id;

    public TableNotFoundException(Table.Id id) {
        super(TableErrorMessage.TABLE_NOT_FOUND_BY_ID, ErrorProperty.of("id", id.value()));
        this.id = id;
    }
}
