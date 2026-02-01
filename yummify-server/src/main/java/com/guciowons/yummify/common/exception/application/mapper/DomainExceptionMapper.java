package com.guciowons.yummify.common.exception.application.mapper;

import com.guciowons.yummify.common.exception.application.ApiException;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;

public interface DomainExceptionMapper {
    ApiException mapToApiException(DomainException exception);
}
