package com.guciowons.yummify.menu.exception;

import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorLocationType;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpStatus;

public class MenuIsNotActiveException extends SingleApiErrorException {
  public MenuIsNotActiveException() {
    super(ApiErrorDTO.builder(ErrorMessage.MENU_IS_NOT_ACTIVE, HttpStatus.BAD_REQUEST)
            .errorLocationType(ErrorLocationType.PATH_PARAM)
            .location("id")
    );
  }
}
