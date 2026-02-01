package com.guciowons.yummify.ingredient.application.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.common.exception.domain.model.CommonErrorMessage;
import com.guciowons.yummify.ingredient.domain.exception.IngredientNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.givenIngredientId;
import static org.assertj.core.api.Assertions.assertThat;

class IngredientDomainExceptionMapperTest {
    private final IngredientDomainExceptionMapper underTest = new IngredientDomainExceptionMapper();

    @Test
    void shouldMapIngredientNotFoundExceptionToApiException() {
        // given
        var ingredientId = givenIngredientId(1);
        var exception = new IngredientNotFoundException(ingredientId);

        // when
        var result = underTest.mapToApiException(exception);

        // then
        assertThat(result.getCause()).isEqualTo(exception);
        assertThat(result.getErrorMessage()).isEqualTo(IngredientErrorMessage.INGREDIENT_NOT_FOUND_BY_ID);
        assertThat(result.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getProperties()).containsEntry("id", ingredientId.value().toString());
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
