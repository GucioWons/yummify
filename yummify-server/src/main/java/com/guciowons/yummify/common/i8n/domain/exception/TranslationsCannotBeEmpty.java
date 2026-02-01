package com.guciowons.yummify.common.i8n.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;

public class TranslationsCannotBeEmpty extends DomainException {
    public TranslationsCannotBeEmpty() {
        super("Translations cannot be empty");
    }
}
