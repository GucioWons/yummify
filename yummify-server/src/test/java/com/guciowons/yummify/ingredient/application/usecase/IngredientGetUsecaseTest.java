package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.ingredient.domain.exception.IngredientErrorMessage;
import com.guciowons.yummify.ingredient.domain.exception.IngredientNotFoundException;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import com.guciowons.yummify.ingredient.fixture.IngredientFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class IngredientGetUsecaseTest {
    private IngredientGetUsecase underTest;

    private final IngredientRepository ingredientRepository = mock(IngredientRepository.class);

    @BeforeEach
    void setUp() {
        underTest = new IngredientGetUsecase(ingredientRepository);
    }

    @Test
    void shouldReturnIngredient() {
        // given
        var restaurantId = UUID.randomUUID();
        var ingredientId = UUID.randomUUID();
        var ingredient = IngredientFixture.withIdEntity(ingredientId);

        when(ingredientRepository.findByIdAndRestaurantId(ingredientId, restaurantId))
                .thenReturn(Optional.of(ingredient));

        // when
        var result = underTest.getById(ingredientId, restaurantId);

        // then
        verify(ingredientRepository).findByIdAndRestaurantId(ingredientId, restaurantId);

        assertThat(result).isEqualTo(ingredient);
    }

    @Test
    void shouldThrowException_WhenIngredientNotFound() {
        // given
        var restaurantId = UUID.randomUUID();
        var ingredientId = UUID.randomUUID();

        when(ingredientRepository.findByIdAndRestaurantId(ingredientId, restaurantId))
                .thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> underTest.getById(ingredientId, restaurantId))
                .isInstanceOf(IngredientNotFoundException.class)
                .satisfies(ex -> {
                    IngredientNotFoundException e = (IngredientNotFoundException) ex;
                    assertIngredientNotFoundException(e, ingredientId);
                });

        // then
        verify(ingredientRepository).findByIdAndRestaurantId(ingredientId, restaurantId);
    }

    void assertIngredientNotFoundException(IngredientNotFoundException ex, UUID ingredientId) {
        assertThat(ex.getDomainError().errorMessage())
                .isEqualTo(IngredientErrorMessage.INGREDIENT_NOT_FOUND_BY_ID);

        assertThat(ex.getDomainError().properties())
                .extractingByKey("id")
                .asString()
                .contains(ingredientId.toString());
    }
}