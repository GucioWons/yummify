package com.guciowons.yummify.restaurant.application.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import com.guciowons.yummify.restaurant.domain.exception.RestaurantNotFoundException;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.exception.RestaurantDomainExceptionMapper;
import com.guciowons.yummify.restaurant.domain.exception.message.RestaurantErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;

class RestaurantDomainExceptionMapperTest {
    private final RestaurantDomainExceptionMapper underTest = new RestaurantDomainExceptionMapper();

    @Test
    void shouldMapRestaurantNotFoundExceptionToApiException() {
        // given
        var restaurantId = givenRestaurantId(1);
        var exception = new RestaurantNotFoundException(restaurantId);

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(RestaurantErrorMessage.RESTAURANT_NOT_FOUND_BY_ID);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getProperties()).containsEntry("id", restaurantId.value().toString());
    }

    @Test
    void shouldMapNotImplementedExceptionToApiException() {
        // given
        var exception = new DomainException("Exception message");

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(CommonErrorMessage.DOMAIN_EXCEPTION_HANDLING_NOT_IMPLEMENTED);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
        assertThat(result.getProperties()).isNull();
    }
}
