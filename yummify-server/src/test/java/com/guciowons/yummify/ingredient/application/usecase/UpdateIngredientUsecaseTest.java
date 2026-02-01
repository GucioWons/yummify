package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.ingredient.application.service.IngredientLookupService;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.ingredient.application.fixture.IngredientApplicationFixture.givenUpdateIngredientCommand;
import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.givenIngredient;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UpdateIngredientUsecaseTest {
    private final IngredientLookupService ingredientLookupService = mock(IngredientLookupService.class);
    private final IngredientRepository ingredientRepository = mock(IngredientRepository.class);

    private final UpdateIngredientUsecase underTest = new UpdateIngredientUsecase(ingredientLookupService, ingredientRepository);

    @Test
    void shouldUpdateIngredient() {
        // given
        var command = givenUpdateIngredientCommand();
        var ingredient = givenIngredient(2);

        when(ingredientLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(ingredient);
        when(ingredientRepository.save(ingredient)).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        var result = underTest.update(command);

        // then
        verify(ingredientLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());
        verify(ingredientRepository).save(ingredient);

        assertThat(result.getId()).isEqualTo(ingredient.getId());
        assertThat(result.getRestaurantId()).isEqualTo(ingredient.getRestaurantId());
        assertThat(result.getName()).isEqualTo(command.name());
    }
}
