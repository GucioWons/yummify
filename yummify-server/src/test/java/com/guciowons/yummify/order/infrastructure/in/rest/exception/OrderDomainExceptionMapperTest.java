package com.guciowons.yummify.order.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import com.guciowons.yummify.order.domain.exception.OrderTableNotFoundException;
import com.guciowons.yummify.order.domain.exception.message.OrderErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.givenOrderTableId;
import static org.assertj.core.api.Assertions.assertThat;

class OrderDomainExceptionMapperTest {
    private final OrderDomainExceptionMapper underTest = new OrderDomainExceptionMapper();

    @Test
    void shouldMapOrderTableNotFoundExceptionToApiException() {
        // given
        var tableId = givenOrderTableId(1);
        var exception = new OrderTableNotFoundException(tableId);

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(OrderErrorMessage.ORDER_TABLE_NOT_FOUND_BY_ID);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getProperties())
                .containsEntry("id", tableId.value().toString());
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