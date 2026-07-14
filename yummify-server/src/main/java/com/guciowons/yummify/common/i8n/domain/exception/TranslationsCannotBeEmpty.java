package com.guciowons.yummify.common.i8n.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;

public class TranslationsCannotBeEmpty extends DomainException {
    public TranslationsCannotBeEmpty() {
        super(CommonErrorMessage.UNEXPECTED_SERVER_ERROR);
    }
}
