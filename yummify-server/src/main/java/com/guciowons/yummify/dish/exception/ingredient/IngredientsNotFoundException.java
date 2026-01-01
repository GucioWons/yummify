package com.guciowons.yummify.dish.exception.ingredient;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorLocationType;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class IngredientsNotFoundException extends SingleApiErrorException {
    public IngredientsNotFoundException(List<UUID> ids) {
        super(ApiErrorDTO.builder(ErrorMessage.INGREDIENT_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND)
                .errorLocationType(ErrorLocationType.BODY)
                .location("ingredients")
                .textParam("ids", ids.stream().map(UUID::toString).collect(Collectors.joining(",")))
        );
    }
}
