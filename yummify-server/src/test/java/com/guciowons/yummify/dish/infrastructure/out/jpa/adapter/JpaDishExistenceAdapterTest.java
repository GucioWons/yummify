package com.guciowons.yummify.dish.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.dish.infrastructure.out.jpa.repository.JpaDishRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDishId;
import static com.guciowons.yummify.dish.domain.fixture.DishDomainFixture.givenDishRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JpaDishExistenceAdapterTest {
    private final JpaDishRepository jpaDishRepository = mock(JpaDishRepository.class);

    private final JpaDishExistenceAdapter underTest = new JpaDishExistenceAdapter(jpaDishRepository);

    @Test
    void shouldReturnEmptyList_WhenIdsIsNull() {
        // given
        var restaurantId = givenDishRestaurantId(1).value();

        // when
        var result = underTest.findMissing(null, restaurantId);

        // then
        verify(jpaDishRepository, never()).findExistingIdsByRestaurantId(any(), any());

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnEmptyList_WhenIdsIsEmpty() {
        // given
        var restaurantId = givenDishRestaurantId(1).value();

        // when
        var result = underTest.findMissing(List.of(), restaurantId);

        // then
        verify(jpaDishRepository, never()).findExistingIdsByRestaurantId(any(), any());

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnAllIds_WhenNoneExistInRepository() {
        // given
        var ids = List.of(givenDishId(1).value(), givenDishId(2).value());
        var restaurantId = givenDishRestaurantId(1).value();

        when(jpaDishRepository.findExistingIdsByRestaurantId(ids, restaurantId)).thenReturn(Set.of());

        // when
        var result = underTest.findMissing(ids, restaurantId);

        // then
        verify(jpaDishRepository).findExistingIdsByRestaurantId(ids, restaurantId);

        assertThat(result).isEqualTo(ids);
    }

    @Test
    void shouldReturnOnlyMissingIds_WhenSomeExist() {
        // given
        var missingId = givenDishId(2).value();
        var existingId = givenDishId(1).value();
        var ids = List.of(existingId, missingId);
        var restaurantId = givenDishRestaurantId(1).value();

        when(jpaDishRepository.findExistingIdsByRestaurantId(ids, restaurantId)).thenReturn(Set.of(existingId));

        // when
        List<UUID> result = underTest.findMissing(ids, restaurantId);

        // then
        verify(jpaDishRepository).findExistingIdsByRestaurantId(ids, restaurantId);

        assertThat(result).containsExactly(missingId);
    }

    @Test
    void shouldReturnEmptyList_WhenAllIdsExist() {
        // given
        var firstExistingId = givenDishId(1).value();
        var secondExistingId = givenDishId(2).value();
        var ids = List.of(firstExistingId, secondExistingId);
        var restaurantId = givenDishRestaurantId(1).value();

        when(jpaDishRepository.findExistingIdsByRestaurantId(ids, restaurantId))
                .thenReturn(Set.of(firstExistingId, secondExistingId));

        // when
        List<UUID> result = underTest.findMissing(ids, restaurantId);

        // then
        verify(jpaDishRepository).findExistingIdsByRestaurantId(ids, restaurantId);

        assertThat(result).isEmpty();
    }
}