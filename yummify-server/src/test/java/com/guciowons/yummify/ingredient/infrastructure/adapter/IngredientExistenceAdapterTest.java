package com.guciowons.yummify.ingredient.infrastructure.adapter;

import com.guciowons.yummify.ingredient.domain.repository.IngredientRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.givenIngredient;
import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.givenIngredientId;
import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class IngredientExistenceAdapterTest {
    private final IngredientRepository ingredientRepository = mock(IngredientRepository.class);

    private final IngredientExistenceAdapter underTest = new IngredientExistenceAdapter(ingredientRepository);

    @Test
    void shouldReturnEmptyList_WhenIdsIsNull() {
        // given
        var restaurantId = givenRestaurantId(1);

        // when
        var result = underTest.findMissing(null, restaurantId);

        // then
        verify(ingredientRepository, never()).findByIdInAndRestaurantId(any(), any());

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnEmptyList_WhenIdsIsEmpty() {
        // given
        var restaurantId = givenRestaurantId(1);

        // when
        var result = underTest.findMissing(List.of(), restaurantId);

        // then
        verify(ingredientRepository, never()).findByIdInAndRestaurantId(any(), any());

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnAllIds_WhenNoneExistInRepository() {
        // given
        var ids = List.of(givenIngredientId(1).value(), givenIngredientId(2).value());
        var restaurantId = givenRestaurantId(1);

        when(ingredientRepository.findByIdInAndRestaurantId(ids, restaurantId)).thenReturn(List.of());

        // when
        var result = underTest.findMissing(ids, restaurantId);

        // then
        assertThat(result).isEqualTo(ids);
    }

    @Test
    void shouldReturnOnlyMissingIds_WhenSomeExist() {
        // given
        var missingId = givenIngredientId(2).value();
        var existingIngredient = givenIngredient(1);
        var ids = List.of(existingIngredient.getId().value(), missingId);
        var restaurantId = givenRestaurantId(1);

        when(ingredientRepository.findByIdInAndRestaurantId(ids, restaurantId)).thenReturn(List.of(existingIngredient));

        // when
        List<UUID> result = underTest.findMissing(ids, restaurantId);

        // then
        verify(ingredientRepository).findByIdInAndRestaurantId(ids, restaurantId);

        assertThat(result).containsExactly(missingId);
    }

    @Test
    void shouldReturnEmptyList_WhenAllIdsExist() {
        // given
        var firstIngredient = givenIngredient(1);
        var secondIngredient = givenIngredient(2);
        var ids = List.of(firstIngredient.getId().value(), secondIngredient.getId().value());
        var restaurantId = givenRestaurantId(1);

        when(ingredientRepository.findByIdInAndRestaurantId(ids, restaurantId))
                .thenReturn(List.of(firstIngredient, secondIngredient));

        // when
        List<UUID> result = underTest.findMissing(ids, restaurantId);

        // then
        assertThat(result).isEmpty();
    }
}
