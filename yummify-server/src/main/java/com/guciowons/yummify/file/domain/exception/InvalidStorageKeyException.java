package com.guciowons.yummify.file.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import lombok.Getter;

@Getter
public class InvalidStorageKeyException extends DomainException {
    private final Reason reason;

    private InvalidStorageKeyException(Reason reason) {
        super("Invalid storage key");
        this.reason = reason;
    }

    public static InvalidStorageKeyException blank() {
        return new InvalidStorageKeyException(Reason.BLANK);
    }

    public static InvalidStorageKeyException startsWithSlash() {
        return new InvalidStorageKeyException(Reason.STARTS_WITH_SLASH);
    }

    public static InvalidStorageKeyException containsDots() {
        return new InvalidStorageKeyException(Reason.CONTAINS_DOTS);
    }

    public enum Reason {
        BLANK, STARTS_WITH_SLASH, CONTAINS_DOTS
    }
}
