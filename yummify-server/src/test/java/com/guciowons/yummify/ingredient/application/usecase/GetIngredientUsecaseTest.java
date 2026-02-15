package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.ingredient.application.service.IngredientLookupService;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.ingredient.application.fixture.IngredientApplicationFixture.givenGetIngredientQuery;
import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.givenIngredient;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetIngredientUsecaseTest {
    private final IngredientLookupService ingredientLookupService = mock(IngredientLookupService.class);

    private final GetIngredientUsecase underTest = new GetIngredientUsecase(ingredientLookupService);

    @Test
    void shouldGetIngredient() {
        // given
        var command = givenGetIngredientQuery();
        var ingredient = givenIngredient(1);

        when(ingredientLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(ingredient);

        // when
        var result = underTest.getById(command);

        // then
        verify(ingredientLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());

        assertThat(result).isEqualTo(ingredient);
    }
}
