package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.ingredient.application.fixture.IngredientApplicationFixture.givenCreateIngredientCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateIngredientUsecaseTest {
    private final IngredientRepository ingredientRepository = mock(IngredientRepository.class);

    private final CreateIngredientUsecase createIngredientUsecase = new CreateIngredientUsecase(ingredientRepository);

    @Test
    void shouldCreateIngredient() {
        // given
        var command = givenCreateIngredientCommand();

        // when
        var result = createIngredientUsecase.create(command);

        // then
        verify(ingredientRepository).save(any());

        assertThat(result.getId()).isNotNull();
        assertThat(result.getRestaurantId()).isEqualTo(command.restaurantId());
        assertThat(result.getName()).isEqualTo(command.name());
    }
}
