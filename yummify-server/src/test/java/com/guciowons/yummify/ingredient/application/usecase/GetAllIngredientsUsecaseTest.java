package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.guciowons.yummify.ingredient.application.fixture.IngredientApplicationFixture.givenGetAllIngredientsCommand;
import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.givenIngredient;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetAllIngredientsUsecaseTest {
    private final IngredientRepository ingredientRepository = mock(IngredientRepository.class);

    private final GetAllIngredientsUsecase underTest = new GetAllIngredientsUsecase(ingredientRepository);

    @Test
    void shouldGetAllIngredients() {
        // given
        var command = givenGetAllIngredientsCommand();
        var ingredients = List.of(givenIngredient(1), givenIngredient(2), givenIngredient(3));

        when(ingredientRepository.findAllByRestaurantId(command.restaurantId())).thenReturn(ingredients);

        // when
        var result = underTest.getAll(command);

        // then
        verify(ingredientRepository).findAllByRestaurantId(command.restaurantId());

        assertThat(result).isEqualTo(ingredients);
    }
}
