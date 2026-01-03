package com.guciowons.yummify.ingredient.application.usecase;

import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import com.guciowons.yummify.ingredient.fixture.IngredientFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class IngredientGetAllUsecaseTest {
    private IngredientGetAllUsecase underTest;

    private final IngredientRepository ingredientRepository = mock(IngredientRepository.class);

    @BeforeEach
    void setUp() {
        underTest = new IngredientGetAllUsecase(ingredientRepository);
    }

    @Test
    void shouldGetAllIngredients() {
        // given
        var restaurantId = UUID.randomUUID();
        var ingredients = List.of(IngredientFixture.withIdEntity(UUID.randomUUID()));

        when(ingredientRepository.findAllByRestaurantId(restaurantId)).thenReturn(ingredients);

        // when
        var result = underTest.getAll(restaurantId);

        // then
        verify(ingredientRepository).findAllByRestaurantId(restaurantId);

        assertThat(result).isEqualTo(ingredients);
    }

}