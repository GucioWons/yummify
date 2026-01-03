package com.guciowons.yummify.ingredient.domain.adapter;

import com.guciowons.yummify.ingredient.domain.exception.IngredientErrorMessage;
import com.guciowons.yummify.ingredient.domain.exception.IngredientsNotFoundException;
import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import com.guciowons.yummify.ingredient.fixture.IngredientFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class IngredientExistenceAdapterTest {
    private IngredientExistenceAdapter underTest;

    private final IngredientRepository ingredientRepository = mock(IngredientRepository.class);

    @BeforeEach
    void setUp() {
        underTest = new IngredientExistenceAdapter(ingredientRepository);
    }

    @Test
    void shouldNotThrowException_WhenNoMissingIds() {
        // given
        var restaurantId = UUID.randomUUID();
        var existingId = UUID.randomUUID();
        var ingredientIds = List.of(existingId);
        var ingredients = List.of(IngredientFixture.withIdEntity(existingId));

        when(ingredientRepository.findByIdInAndRestaurantId(ingredientIds, restaurantId))
                .thenReturn(ingredients);

        // when
        underTest.assertAllExist(ingredientIds, restaurantId);

        // then
        verify(ingredientRepository).findByIdInAndRestaurantId(ingredientIds, restaurantId);
    }

    @Test
    void shouldThrowException_WhenMissingIds() {
        // given
        var restaurantId = UUID.randomUUID();
        var existingId = UUID.randomUUID();
        var missingId = UUID.randomUUID();
        var ingredientIds = List.of(existingId, missingId);
        var existingIngredients = List.of(IngredientFixture.withIdEntity(existingId));

        when(ingredientRepository.findByIdInAndRestaurantId(ingredientIds, restaurantId))
                .thenReturn(existingIngredients);

        // when
        assertThatThrownBy(() -> underTest.assertAllExist(ingredientIds, restaurantId))
                .isInstanceOf(IngredientsNotFoundException.class)
                .satisfies(ex -> {
                    IngredientsNotFoundException e = (IngredientsNotFoundException) ex;
                    assertIngredientsNotFoundException(e, missingId);
                });

        // then
        verify(ingredientRepository).findByIdInAndRestaurantId(ingredientIds, restaurantId);
    }

    @Test
    void shouldNotCheckExistence_WhenIngredientIdsAreNull() {
        // given
        var restaurantId = UUID.randomUUID();

        // when
        underTest.assertAllExist(null, restaurantId);

        // then
        verify(ingredientRepository, never()).findByIdInAndRestaurantId(any(), any());
    }

    @Test
    void shouldNotCheckExistence_WhenIngredientIdsAreEmpty() {
        // given
        var restaurantId = UUID.randomUUID();

        // when
        underTest.assertAllExist(Collections.emptyList(), restaurantId);

        // then
        verify(ingredientRepository, never()).findByIdInAndRestaurantId(any(), any());
    }

    void assertIngredientsNotFoundException(IngredientsNotFoundException ex, UUID missingId) {
        assertThat(ex.getDomainError().errorMessage())
                .isEqualTo(IngredientErrorMessage.INGREDIENTS_NOT_FOUND_BY_IDS);

        assertThat(ex.getDomainError().properties())
                .extractingByKey("ids")
                .asString()
                .contains(missingId.toString());
    }
}