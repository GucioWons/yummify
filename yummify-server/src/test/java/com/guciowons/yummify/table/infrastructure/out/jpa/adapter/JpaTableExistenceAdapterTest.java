package com.guciowons.yummify.table.infrastructure.out.jpa.adapter;

import com.guciowons.yummify.table.infrastructure.out.jpa.repository.JpaTableRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JpaTableExistenceAdapterTest {
    private final JpaTableRepository jpaTableRepository = mock(JpaTableRepository.class);

    private final JpaTableExistenceAdapter underTest = new JpaTableExistenceAdapter(jpaTableRepository);

    @Test
    void shouldReturnTrueWhenTableExists() {
        // given
        var tableId = UUID.randomUUID();
        var restaurantId = UUID.randomUUID();

        when(jpaTableRepository.existsByIdAndRestaurantId(tableId, restaurantId)).thenReturn(true);

        // when
        var result = underTest.exists(tableId, restaurantId);

        // then
        verify(jpaTableRepository).existsByIdAndRestaurantId(tableId, restaurantId);

        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseWhenTableDoesNotExist() {
        // given
        var tableId = UUID.randomUUID();
        var restaurantId = UUID.randomUUID();

        when(jpaTableRepository.existsByIdAndRestaurantId(tableId, restaurantId)).thenReturn(false);

        // when
        var result = underTest.exists(tableId, restaurantId);

        // then
        verify(jpaTableRepository).existsByIdAndRestaurantId(tableId, restaurantId);

        assertThat(result).isFalse();
    }
}