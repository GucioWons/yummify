package com.guciowons.yummify.common.security.domain;


import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SecurityErrorMessage implements ErrorMessage {
    ACCESS_DENIED("Access denied"),
    UNAUTHORIZED("You are not logged in");

    private final String message;
}
