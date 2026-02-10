package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;

public class DraftMenuVersionNotFoundException extends DomainException {
    public DraftMenuVersionNotFoundException() {
        super("Draft menu version not found");
    }
}
