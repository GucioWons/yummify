package com.guciowons.yummify.common.exception.infrastructure.in.rest.handler;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;

public class TestDomainException extends DomainException {
    public TestDomainException() {
        super(CommonErrorMessage.DOMAIN_EXCEPTION_HANDLING_NOT_IMPLEMENTED, ErrorProperty.of("test", "test"));
    }
}
