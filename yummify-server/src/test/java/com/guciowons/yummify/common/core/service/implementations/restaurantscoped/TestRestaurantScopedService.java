package com.guciowons.yummify.common.core.service.implementations.restaurantscoped;

import com.guciowons.yummify.common.core.service.RestaurantScopedService;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class TestRestaurantScopedService extends RestaurantScopedService<
        TestRestaurantScopedEntity,
        TestRestaurantScopedDTO,
        TestRestaurantScopedRepository,
        TestRestaurantScopedMapper
        > {

    public TestRestaurantScopedService(TestRestaurantScopedRepository repository, TestRestaurantScopedMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected SingleApiErrorException getNotFoundException(UUID id) {
        return new SingleApiErrorException(
                ApiErrorDTO.builder(ErrorMessage.UNEXPECTED_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }
}
