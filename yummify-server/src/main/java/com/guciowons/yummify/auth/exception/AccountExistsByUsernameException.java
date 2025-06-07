package com.guciowons.yummify.auth.exception;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorLocationType;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpStatus;

public class AccountExistsByUsernameException extends SingleApiErrorException {
    public AccountExistsByUsernameException(String location) {
        super(ApiErrorDTO.builder(ErrorMessage.KEYCLOAK_ACCOUNT_EXISTS_BY_USERNAME, HttpStatus.CONFLICT)
                .errorLocationType(ErrorLocationType.BODY)
                .location(location));
    }
}
