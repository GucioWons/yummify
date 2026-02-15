package com.guciowons.yummify.ingredient.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.ingredient.infrastructure.out.jpa.repository.JpaIngredientRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.givenIngredientId;
import static com.guciowons.yummify.ingredient.domain.fixture.IngredientDomainFixture.givenIngredientRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JpaIngredientExistenceAdapterTest {
    private final JpaIngredientRepository jpaIngredientRepository = mock(JpaIngredientRepository.class);

    private final JpaIngredientExistenceAdapter underTest = new JpaIngredientExistenceAdapter(jpaIngredientRepository);

    @Test
    void shouldReturnEmptyList_WhenIdsIsNull() {
        // given
        var restaurantId = givenIngredientRestaurantId(1).value();

        // when
        var result = underTest.findMissing(null, restaurantId);

        // then
        verify(jpaIngredientRepository, never()).findExistingIdsByRestaurantId(any(), any());

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnEmptyList_WhenIdsIsEmpty() {
        // given
        var restaurantId = givenIngredientRestaurantId(1).value();

        // when
        var result = underTest.findMissing(List.of(), restaurantId);

        // then
        verify(jpaIngredientRepository, never()).findExistingIdsByRestaurantId(any(), any());

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnAllIds_WhenNoneExistInRepository() {
        // given
        var ids = List.of(givenIngredientId(1).value(), givenIngredientId(2).value());
        var restaurantId = givenIngredientRestaurantId(1).value();

        when(jpaIngredientRepository.findExistingIdsByRestaurantId(ids, restaurantId)).thenReturn(List.of());

        // when
        var result = underTest.findMissing(ids, restaurantId);

        // then
        verify(jpaIngredientRepository).findExistingIdsByRestaurantId(ids, restaurantId);

        assertThat(result).isEqualTo(ids);
    }

    @Test
    void shouldReturnOnlyMissingIds_WhenSomeExist() {
        // given
        var missingId = givenIngredientId(2).value();
        var existingId = givenIngredientId(1).value();
        var ids = List.of(existingId, missingId);
        var restaurantId = givenIngredientRestaurantId(1).value();

        when(jpaIngredientRepository.findExistingIdsByRestaurantId(ids, restaurantId)).thenReturn(List.of(existingId));

        // when
        List<UUID> result = underTest.findMissing(ids, restaurantId);

        // then
        verify(jpaIngredientRepository).findExistingIdsByRestaurantId(ids, restaurantId);

        assertThat(result).containsExactly(missingId);
    }

    @Test
    void shouldReturnEmptyList_WhenAllIdsExist() {
        // given
        var firstExistingId = givenIngredientId(1).value();
        var secondExistingId = givenIngredientId(2).value();
        var ids = List.of(firstExistingId, secondExistingId);
        var restaurantId = givenIngredientRestaurantId(1).value();

        when(jpaIngredientRepository.findExistingIdsByRestaurantId(ids, restaurantId))
                .thenReturn(List.of(firstExistingId, secondExistingId));

        // when
        List<UUID> result = underTest.findMissing(ids, restaurantId);

        // then
        verify(jpaIngredientRepository).findExistingIdsByRestaurantId(ids, restaurantId);

        assertThat(result).isEmpty();
    }
}
