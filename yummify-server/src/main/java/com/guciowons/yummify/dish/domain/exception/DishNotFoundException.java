package com.guciowons.yummify.dish.domain.exception;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorLocationType;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class DishNotFoundException extends SingleApiErrorException {
    public DishNotFoundException(UUID id) {
        super(ApiErrorDTO.builder(ErrorMessage.INGREDIENT_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND)
                .errorLocationType(ErrorLocationType.PATH_PARAM)
                .location("id")
                .textParam("id", id.toString())
        );
    }
}
