package com.guciowons.yummify.common.core.service.implementations.translatable;

import com.guciowons.yummify.common.core.service.TranslatableRestaurantScopedService;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.exception.dto.ApiErrorDTO;
import com.guciowons.yummify.common.exception.enumerated.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class TestTranslatableRestaurantScopedService extends TranslatableRestaurantScopedService<
        TestTranslatableRestaurantScopedEntity,
        TestTranslatableRestaurantScopedDTO,
        TestTranslatableRestaurantScopedManageDTO,
        TestTranslatableRestaurantScopedClientDTO,
        TestTranslatableRestaurantScopedRepository,
        TestTranslatableRestaurantScopedMapper
        >
{
    public TestTranslatableRestaurantScopedService(TestTranslatableRestaurantScopedRepository repository, TestTranslatableRestaurantScopedMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected SingleApiErrorException getNotFoundException(UUID id) {
        return new SingleApiErrorException(
                ApiErrorDTO.builder(ErrorMessage.UNEXPECTED_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }
}
