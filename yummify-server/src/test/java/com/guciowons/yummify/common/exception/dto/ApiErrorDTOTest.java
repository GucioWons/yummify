package com.guciowons.yummify.common.exception.dto;

import com.guciowons.yummify.common.exception.enumerated.ErrorLocationType;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ApiErrorDTOTest {
    @Test
    void shouldBuildWithMinimalFields() {
        ApiErrorDTO error = ApiErrorDTO
                .builder(ErrorMessage.UNEXPECTED_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR)
                .build();

        assertEquals(ErrorMessage.UNEXPECTED_SERVER_ERROR, error.getErrorMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, error.getHttpStatus());
        assertEquals(ErrorMessage.UNEXPECTED_SERVER_ERROR.getMessage(), error.getErrorMessageString());
        assertNull(error.getErrorLocationType());
        assertNull(error.getLocation());
    }

    @Test
    void shouldBuildWithAllFields() {
        ApiErrorDTO error = ApiErrorDTO
                .builder(ErrorMessage.UNEXPECTED_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR)
                .errorLocationType(ErrorLocationType.BODY)
                .location("location")
                .build();

        assertEquals(ErrorMessage.UNEXPECTED_SERVER_ERROR, error.getErrorMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, error.getHttpStatus());
        assertEquals(ErrorMessage.UNEXPECTED_SERVER_ERROR.getMessage(), error.getErrorMessageString());
        assertEquals(ErrorLocationType.BODY, error.getErrorLocationType());
        assertEquals("location", error.getLocation());
    }

    @Test
    void shouldFillInTextPlaceholders() {
        UUID id = UUID.randomUUID();

        ApiErrorDTO error = ApiErrorDTO
                .builder(ErrorMessage.RESTAURANT_NOT_FOUND_BY_ID, HttpStatus.NOT_FOUND)
                .textParam("id", id)
                .build();

        assertEquals("Could not find restaurant with ID " + id, error.getErrorMessageString());
    }

}