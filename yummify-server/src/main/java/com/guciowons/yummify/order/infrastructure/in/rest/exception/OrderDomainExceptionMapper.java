package com.guciowons.yummify.order.infrastructure.in.rest.exception;

import com.guciowons.yummify.common.core.application.annotation.ExceptionMapper;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.ApiException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.mapper.DomainExceptionMapper;

@ExceptionMapper
public class OrderDomainExceptionMapper implements DomainExceptionMapper {
    @Override
    public ApiException mapToApiException(DomainException exception) {
        return ApiException.notImplemented(exception);
    }
}
