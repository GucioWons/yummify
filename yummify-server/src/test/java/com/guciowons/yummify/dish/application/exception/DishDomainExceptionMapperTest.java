package com.guciowons.yummify.dish.application.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import com.guciowons.yummify.dish.domain.exception.DishIngredientsNotFoundException;
import com.guciowons.yummify.dish.domain.exception.DishNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDishId;
import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDishIngredientIds;
import static org.assertj.core.api.Assertions.assertThat;

class DishDomainExceptionMapperTest {
    private final DishDomainExceptionMapper underTest = new DishDomainExceptionMapper();

    @Test
    void shouldMapDishNotFoundExceptionToApiException() {
        // given
        var dishId = givenDishId(1);
        var exception = new DishNotFoundException(dishId);

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(DishErrorMessage.DISH_NOT_FOUND_BY_ID);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getProperties()).containsEntry("id", dishId.value().toString());
    }

    @Test
    void shouldMapDishIngredientsNotFoundExceptionToApiException() {
        // given
        var ingredientIds = givenDishIngredientIds(1);
        var exception = new DishIngredientsNotFoundException(ingredientIds);

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(DishErrorMessage.DISH_INGREDIENTS_NOT_FOUND_BY_IDS);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getProperties())
                .containsEntry("ids", String.join(", ", ingredientIds.stream().map(UUID::toString).toList()));
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