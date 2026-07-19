package com.guciowons.yummify.order.domain.entity;

import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class OrderItemTest {
    @Test
    void shouldCreateOrderItem() {
        // given
        var dishId = givenOrderItemDishId(1);
        var snapshot = givenOrderItemDishSnapshot(1);

        // when
        var orderItem = OrderItem.create(dishId, snapshot, 1);

        // then
        assertThat(orderItem.getId()).isNotNull();
        assertThat(orderItem.getDishId()).isEqualTo(dishId);
        assertThat(orderItem.getDishSnapshot()).isEqualTo(snapshot);
        assertThat(orderItem.getQuantity()).isEqualTo(1);
    }

    @Test
    void shouldIncreaseQuantity() {
        // given
        var orderItem = givenOrderItem(1);

        // when
        var returned = orderItem.increaseQuantity(3);

        // then
        assertThat(returned).isSameAs(orderItem);
        assertThat(returned.getQuantity()).isEqualTo(4);
    }
}
