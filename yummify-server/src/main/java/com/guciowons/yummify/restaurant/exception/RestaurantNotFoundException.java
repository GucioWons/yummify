package com.guciowons.yummify.restaurant.exception;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorLocationType;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class RestaurantNotFoundException extends SingleApiErrorException {
    public RestaurantNotFoundException(UUID id) {
        super(ApiErrorDTO.builder(ErrorMessage.RESTAURANT_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND)
                .errorLocationType(ErrorLocationType.AUTH)
                .location("jwt")
                .textParam("id", id)
        );
    }
}
