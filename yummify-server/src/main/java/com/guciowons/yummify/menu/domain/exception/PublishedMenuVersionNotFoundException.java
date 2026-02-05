package com.guciowons.yummify.menu.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;

public class PublishedMenuVersionNotFoundException extends DomainException {
    public PublishedMenuVersionNotFoundException() {
        super("Published menu version not found");
    }
}
