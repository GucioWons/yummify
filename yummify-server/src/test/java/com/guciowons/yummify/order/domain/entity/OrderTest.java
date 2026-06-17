package com.guciowons.yummify.order.domain.entity;

import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.givenOrderRestaurantId;
import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.givenOrderTableId;
import static org.assertj.core.api.Assertions.assertThat;

class OrderTest {
    @Test
    void shouldCreateOrderWithRandomIdAndNewStatus() {
        // given
        var restaurantId = givenOrderRestaurantId(1);
        var tableId = givenOrderTableId(1);

        // when
        var result = Order.create(restaurantId, tableId);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getRestaurantId()).isEqualTo(restaurantId);
        assertThat(result.getTableId()).isEqualTo(tableId);
        assertThat(result.getItems()).isNotNull().isEmpty();
        assertThat(result.getStatus()).isEqualTo(OrderStatus.NEW);
    }

}
