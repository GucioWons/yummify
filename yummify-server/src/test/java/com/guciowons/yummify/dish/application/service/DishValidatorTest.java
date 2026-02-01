package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.dish.domain.exception.DishIngredientsNotFoundException;
import com.guciowons.yummify.ingredient.IngredientExistencePort;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDishIngredientIds;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class DishValidatorTest {
    private final IngredientExistencePort ingredientExistencePort = mock(IngredientExistencePort.class);

    private final DishValidator underTest = new DishValidator(ingredientExistencePort);

    @Test
    void shouldNotThrowException_WhenIngredientIdsAreEmpty() {
        // given
        var restaurantId = givenRestaurantId(1);

        // when
        underTest.validate(List.of(), restaurantId);

        // then
        verify(ingredientExistencePort, never()).findMissing(any(), any());
    }

    @Test
    void shouldNotThrowException_WhenNoMissingIngredientIds() {
        // given
        var ingredientIds = givenDishIngredientIds(1);
        var restaurantId = givenRestaurantId(1);

        when(ingredientExistencePort.findMissing(ingredientIds, restaurantId)).thenReturn(List.of());

        // when
        underTest.validate(ingredientIds, restaurantId);

        // then
        verify(ingredientExistencePort).findMissing(ingredientIds, restaurantId);
    }

    @Test
    void shouldThrowException_WhenMissingIngredientIds() {
        // given
        var ingredientIds = givenDishIngredientIds(1);
        var restaurantId = givenRestaurantId(1);

        when(ingredientExistencePort.findMissing(ingredientIds, restaurantId)).thenReturn(ingredientIds);

        // when
        assertThatThrownBy(() -> underTest.validate(ingredientIds, restaurantId))
                .isInstanceOf(DishIngredientsNotFoundException.class);

        // then
        verify(ingredientExistencePort).findMissing(ingredientIds, restaurantId);
    }
}