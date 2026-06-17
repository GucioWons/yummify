package com.guciowons.yummify.order.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.ApiException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.mapper.DomainExceptionMapper;
import com.guciowons.yummify.order.domain.exception.OrderTableNotFoundException;
import com.guciowons.yummify.order.domain.exception.message.OrderErrorMessage;

import java.util.Map;

@ExceptionMapper
public class OrderDomainExceptionMapper implements DomainExceptionMapper {
    @Override
    public ApiException mapToApiException(DomainException exception) {
        return switch(exception) {
            case OrderTableNotFoundException ex -> mapOrderTableNotFoundException(ex);
            default -> ApiException.notImplemented(exception);
        };
    }

    private ApiException mapOrderTableNotFoundException(OrderTableNotFoundException exception) {
        return ApiException.notFound(
                exception,
                OrderErrorMessage.ORDER_TABLE_NOT_FOUND_BY_ID,
                Map.of("id", exception.getTableId().value().toString())
        );
    }
}
