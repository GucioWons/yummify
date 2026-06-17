package com.guciowons.yummify.order.application.service;

import com.guciowons.yummify.order.domain.exception.OrderTableNotFoundException;
import com.guciowons.yummify.table.TableExistencePort;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.givenOrderRestaurantId;
import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.givenOrderTableId;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class OrderValidatorTest {
    private final TableExistencePort tableExistencePort = mock(TableExistencePort.class);

    private final OrderValidator underTest = new OrderValidator(tableExistencePort);

    @Test
    void shouldNotThrowException_WhenTableExists() {
        // given
        var tableId = givenOrderTableId(1);
        var restaurantId = givenOrderRestaurantId(1);

        when(tableExistencePort.exists(tableId.value(), restaurantId.value())).thenReturn(true);

        // when
        underTest.validate(tableId, restaurantId);

        // then
        verify(tableExistencePort).exists(tableId.value(), restaurantId.value());
    }

    @Test
    void shouldThrowException_WhenMissingIngredientIds() {
        // given
        var tableId = givenOrderTableId(1);
        var restaurantId = givenOrderRestaurantId(1);

        when(tableExistencePort.exists(tableId.value(), restaurantId.value())).thenReturn(false);

        // when
        assertThatThrownBy(() -> underTest.validate(tableId, restaurantId))
                .isInstanceOf(OrderTableNotFoundException.class);

        // then
        verify(tableExistencePort).exists(tableId.value(), restaurantId.value());
    }
}
