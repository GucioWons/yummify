package com.guciowons.yummify.table.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.exception.message.TableErrorMessage;

public class TableNotFoundException extends TableDomainException {
    private TableNotFoundException(ErrorMessage errorMessage, ErrorProperty... properties) {
        super(errorMessage, properties);
    }

    public static TableNotFoundException byId(Table.Id id) {
        return new TableNotFoundException(TableErrorMessage.TABLE_NOT_FOUND_BY_ID, ErrorProperty.of("id", id.value()));
    }

    public static TableNotFoundException byUserId(Table.UserId userId) {
        return new TableNotFoundException(
                TableErrorMessage.TABLE_NOT_FOUND_BY_USER_ID,
                ErrorProperty.of("userId", userId.value())
        );
    }
}
