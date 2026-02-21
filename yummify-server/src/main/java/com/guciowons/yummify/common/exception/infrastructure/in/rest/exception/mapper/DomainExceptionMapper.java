package com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.mapper;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.infrastructure.in.rest.exception.ApiException;

public interface DomainExceptionMapper {
    ApiException mapToApiException(DomainException exception);
}
