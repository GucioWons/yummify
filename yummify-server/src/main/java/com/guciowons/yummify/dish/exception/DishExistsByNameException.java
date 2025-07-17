package com.guciowons.yummify.dish.exception;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorLocationType;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpStatus;

public class DishExistsByNameException extends SingleApiErrorException {
    public DishExistsByNameException(String name) {
        super(ApiErrorDTO.builder(ErrorMessage.RESTAURANT_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND)
                .errorLocationType(ErrorLocationType.BODY)
                .location("name")
                .textParam("name", name)
        );
    }
}
