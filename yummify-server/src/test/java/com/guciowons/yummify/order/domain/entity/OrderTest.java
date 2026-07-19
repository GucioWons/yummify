package com.guciowons.yummify.order.domain.entity;

import com.guciowons.yummify.order.domain.exception.OrderItemNotFoundException;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void shouldAddNewItemWhenDishDoesNotExist() {
        // given
        var order = givenOrder(1);
        var dishId = givenOrderItemDishId(1);
        var snapshot = givenOrderItemDishSnapshot(1);

        // when
        var result = order.addItem(dishId, snapshot, 2);

        // then
        assertThat(order.getItems()).hasSize(1);
        assertThat(order.getItems()).contains(result);
        assertThat(result.getDishId()).isEqualTo(dishId);
        assertThat(result.getDishSnapshot()).isEqualTo(snapshot);
        assertThat(result.getQuantity()).isEqualTo(2);
    }
    @Test
    void shouldIncreaseQuantityWhenDishAlreadyExists() {
        // given
        var order = givenOrder(1);
        var dishId = givenOrderItemDishId(1);
        var snapshot = givenOrderItemDishSnapshot(1);
        order.addItem(dishId, snapshot, 2);

        // when
        var result = order.addItem(dishId, snapshot, 3);

        // then
        assertThat(order.getItems()).hasSize(1);
        assertThat(order.getItems()).contains(result);
        assertThat(result.getQuantity()).isEqualTo(5);
    }

    @Test
    void shouldRemoveItemWhenItemExists() {
        // given
        var order = givenOrder(1);
        var dishId = givenOrderItemDishId(1);
        var snapshot = givenOrderItemDishSnapshot(1);
        var item = order.addItem(dishId, snapshot, 2);

        // when
        order.removeItem(item.getId());

        // then
        assertThat(order.getItems()).isEmpty();
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingItem() {
        // given
        var order = givenOrder(1);
        var nonExistingItemId = givenOrderItemId(999);

        // when + then
        assertThatThrownBy(() -> order.removeItem(nonExistingItemId))
                .isInstanceOf(OrderItemNotFoundException.class);
    }
}
