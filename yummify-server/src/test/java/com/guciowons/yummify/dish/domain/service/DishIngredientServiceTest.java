package com.guciowons.yummify.dish.domain.service;

import com.guciowons.yummify.dish.fixture.DishFixture;
import com.guciowons.yummify.ingredient.exposed.IngredientExistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DishIngredientServiceTest {
    private DishIngredientService underTest;

    private final IngredientExistencePort ingredientExistencePort = mock(IngredientExistencePort.class);

    @BeforeEach
    void setUp() {
        underTest = new DishIngredientService(ingredientExistencePort);
    }

    @Test
    void shouldValidateAndSetIngredientIds_WhenIngredientIdsIsNotEmpty() {
        // given
        var dish = DishFixture.emptyEntity();
        dish.setRestaurantId(UUID.randomUUID());
        dish.setIngredientIds(List.of(UUID.randomUUID()));
        var ingredientIds = List.of(UUID.randomUUID());

        // when
        underTest.replaceIngredients(dish, ingredientIds);

        // then
        verify(ingredientExistencePort).assertAllExist(ingredientIds, dish.getRestaurantId());

        assertThat(dish.getIngredientIds()).isEqualTo(ingredientIds);
    }

    @Test
    void shouldNotValidateAndClearIngredientIds_WhenIngredientIdsIsEmpty() {
        // given
        var dish = DishFixture.emptyEntity();
        dish.setIngredientIds(List.of(UUID.randomUUID()));

        // when
        underTest.replaceIngredients(dish, Collections.emptyList());

        // then
        verify(ingredientExistencePort, never()).assertAllExist(any(), any());

        assertThat(dish.getIngredientIds()).isEmpty();
    }
}