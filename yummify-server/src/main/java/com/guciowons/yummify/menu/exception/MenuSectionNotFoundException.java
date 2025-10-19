package com.guciowons.yummify.menu.exception;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorLocationType;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class MenuSectionNotFoundException extends SingleApiErrorException {
    public MenuSectionNotFoundException(UUID id) {
        super(ApiErrorDTO.builder(ErrorMessage.MENU_SECTION_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND)
                .errorLocationType(ErrorLocationType.PATH_PARAM)
                .location("section/id")
                .textParam("id", id.toString())
        );
    }
}
