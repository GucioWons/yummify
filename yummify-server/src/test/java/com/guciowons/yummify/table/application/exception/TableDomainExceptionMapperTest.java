package com.guciowons.yummify.table.application.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import com.guciowons.yummify.table.domain.exception.TableExistsByNameException;
import com.guciowons.yummify.table.domain.exception.TableNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.guciowons.yummify.table.domain.fixture.TableDomainFixture.givenTableId;
import static com.guciowons.yummify.table.domain.fixture.TableDomainFixture.givenTableName;
import static org.assertj.core.api.Assertions.assertThat;

class TableDomainExceptionMapperTest {
    private final TableDomainExceptionMapper underTest = new TableDomainExceptionMapper();

    @Test
    void shouldMapTableNotFoundExceptionToApiException() {
        // given
        var tableId = givenTableId(1);
        var exception = new TableNotFoundException(tableId);

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(TableErrorMessage.TABLE_NOT_FOUND_BY_ID);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getProperties()).containsEntry("id", tableId.value().toString());
    }

    @Test
    void shouldMapTableExistsByNameExceptionToApiException() {
        // given
        var tableName = givenTableName(1);
        var exception = new TableExistsByNameException(tableName);

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(TableErrorMessage.TABLE_EXISTS_BY_NAME);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(result.getProperties()).containsEntry("name", tableName.value());
    }

    @Test
    void shouldMapNotImplementedExceptionToApiException() {
        // given
        var exception = new DomainException("Exception message");

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(CommonErrorMessage.DOMAIN_EXCEPTION_HANDLING_NOT_IMPLEMENTED);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(result.getProperties()).isNull();
    }

}