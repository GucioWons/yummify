package com.guciowons.yummify.table.application.service;

import com.guciowons.yummify.table.domain.exception.TableNotFoundException;
import com.guciowons.yummify.table.domain.repository.TableRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.guciowons.yummify.table.domain.fixture.TableDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class TableLookupServiceTest {
    private final TableRepository tableRepository = mock(TableRepository.class);
    private final TableLookupService underTest = new TableLookupService(tableRepository);

    @Test
    void shouldGetTable_WhenExists() {
        // given
        var table = givenTable(1);
        var tableId = table.getId();
        var restaurantId = table.getRestaurantId();

        when(tableRepository.findByIdAndRestaurantId(tableId, restaurantId)).thenReturn(Optional.of(table));

        // when
        var result = underTest.getByIdAndRestaurantId(tableId, restaurantId);

        // then
        verify(tableRepository).findByIdAndRestaurantId(tableId, restaurantId);

        assertThat(result).isEqualTo(table);
    }

    @Test
    void shouldThrowException_WhenNotExists() {
        // given
        var tableId = givenTableId(1);
        var restaurantId = givenTableRestaurantId(1);

        when(tableRepository.findByIdAndRestaurantId(tableId, restaurantId)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> underTest.getByIdAndRestaurantId(tableId, restaurantId))
                .isInstanceOf(TableNotFoundException.class);

        // then
        verify(tableRepository).findByIdAndRestaurantId(tableId, restaurantId);
    }
}