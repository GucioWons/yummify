package com.guciowons.yummify.order.domain.entity;

import com.guciowons.yummify.order.domain.exception.InvalidOrderStatusTransitionException;
import com.guciowons.yummify.order.domain.exception.OrderIsEmptyException;
import com.guciowons.yummify.order.domain.exception.OrderIsFinishedException;
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
    void shouldThrowWhenAddingItemToFinishedOrder() {
        // given
        var order = givenOrder(1);
        order.cancel();
        var dishId = givenOrderItemDishId(1);
        var snapshot = givenOrderItemDishSnapshot(1);

        // when + then
        assertThatThrownBy(() -> order.addItem(dishId, snapshot, 2))
                .isInstanceOf(OrderIsFinishedException.class);
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
        var firstItem = order.addItem(givenOrderItemDishId(1), givenOrderItemDishSnapshot(1), 1);
        var secondItem = order.addItem(givenOrderItemDishId(2), givenOrderItemDishSnapshot(2), 1);

        // when
        order.removeItem(firstItem.getId());

        // then
        assertThat(order.getItems()).containsExactly(secondItem);
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

    @Test
    void shouldThrowWhenRemovingItemFromFinishedOrder() {
        // given
        var order = givenOrder(1);
        var item = order.addItem(givenOrderItemDishId(1), givenOrderItemDishSnapshot(1), 1);
        order.cancel();

        // when + then
        assertThatThrownBy(() -> order.removeItem(item.getId()))
                .isInstanceOf(OrderIsFinishedException.class);
    }

    @Test
    void shouldSubmitOrderWhenItHasItems() {
        // given
        var order = givenOrder(1);
        order.addItem(givenOrderItemDishId(1), givenOrderItemDishSnapshot(1), 1);

        // when
        order.submit();

        // then
        assertThat(order.getStatus()).isEqualTo(OrderStatus.SUBMITTED);
    }

    @Test
    void shouldThrowExceptionWhenSubmittingEmptyOrder() {
        // given
        var order = givenOrder(1);

        // when + then
        assertThatThrownBy(order::submit)
                .isInstanceOf(OrderIsEmptyException.class);
    }

    @Test
    void shouldThrowExceptionWhenSubmittingNotNewOrder() {
        // given
        var order = givenOrder(1);
        order.addItem(givenOrderItemDishId(1), givenOrderItemDishSnapshot(1), 1);
        order.submit();

        // when + then
        assertThatThrownBy(order::submit)
                .isInstanceOf(InvalidOrderStatusTransitionException.class);
    }

    @Test
    void shouldCancelOrderAndAllItems() {
        // given
        var order = givenOrder(1);
        order.addItem(givenOrderItemDishId(1), givenOrderItemDishSnapshot(1), 1);
        order.addItem(givenOrderItemDishId(2), givenOrderItemDishSnapshot(2), 1);

        // when
        order.cancel();

        // then
        assertThat(order.getStatus()).isEqualTo(OrderStatus.CANCELLED);
        assertThat(order.getItems())
                .allSatisfy(item -> assertThat(item.getStatus()).isEqualTo(OrderItemStatus.CANCELLED));
    }

    @Test
    void shouldStartItemPreparationAndChangeOrderStatus() {
        // given
        var order = givenOrder(1);
        var item = order.addItem(givenOrderItemDishId(1), givenOrderItemDishSnapshot(1), 1);
        order.submit();

        // when
        var result = order.startItemPreparation(item.getId());

        // then
        assertThat(result).isSameAs(item);
        assertThat(result.getStatus()).isEqualTo(OrderItemStatus.IN_PREPARATION);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.IN_PREPARATION);
    }

    @Test
    void shouldThrowExceptionWhenStartingPreparationForNonExistingItem() {
        // given
        var order = givenOrder(1);
        var nonExistingItemId = givenOrderItemId(1);

        // when + then
        assertThatThrownBy(() -> order.startItemPreparation(nonExistingItemId))
                .isInstanceOf(OrderItemNotFoundException.class);
    }

    @Test
    void shouldFinishItemPreparation() {
        // given
        var order = givenOrder(1);
        var item = order.addItem(givenOrderItemDishId(1), givenOrderItemDishSnapshot(1), 1);
        order.submit();
        order.startItemPreparation(item.getId());

        // when
        var result = order.finishItemPreparation(item.getId());

        // then
        assertThat(result).isSameAs(item);
        assertThat(result.getStatus()).isEqualTo(OrderItemStatus.READY);
        assertThat(order.getStatus()).isEqualTo(OrderStatus.IN_PREPARATION);
    }

    @Test
    void shouldThrowExceptionWhenFinishingPreparationForNonExistingItem() {
        // given
        var order = givenOrder(1);
        var nonExistingItemId = givenOrderItemId(1);

        // when + then
        assertThatThrownBy(() -> order.finishItemPreparation(nonExistingItemId))
                .isInstanceOf(OrderItemNotFoundException.class);
    }

    @Test
    void shouldServeItemWithoutChangingOrderStatusWhenNotAllItemsAreDelivered() {
        // given
        var order = givenOrder(1);
        var firstItem = order.addItem(givenOrderItemDishId(1), givenOrderItemDishSnapshot(1), 1);
        order.addItem(givenOrderItemDishId(2), givenOrderItemDishSnapshot(2), 1);
        order.submit();

        order.startItemPreparation(firstItem.getId());
        order.finishItemPreparation(firstItem.getId());

        // when
        var result = order.serveItem(firstItem.getId());

        // then
        assertThat(result).isSameAs(firstItem);
        assertThat(result.isDelivered()).isTrue();
        assertThat(order.getStatus()).isEqualTo(OrderStatus.IN_PREPARATION);
    }

    @Test
    void shouldChangeOrderStatusToDeliveredWhenAllItemsAreDelivered() {
        // given
        var order = givenOrder(1);
        var item = order.addItem(givenOrderItemDishId(2), givenOrderItemDishSnapshot(2), 1);
        order.submit();

        order.startItemPreparation(item.getId());
        order.finishItemPreparation(item.getId());

        // when
        var result = order.serveItem(item.getId());

        // then
        assertThat(result).isSameAs(item);
        assertThat(result.isDelivered()).isTrue();
        assertThat(order.getStatus()).isEqualTo(OrderStatus.DELIVERED);
    }

    @Test
    void shouldThrowExceptionWhenServingNonExistingItem() {
        // given
        var order = givenOrder(1);
        var nonExistingItemId = givenOrderItemId(1);

        // when + then
        assertThatThrownBy(() -> order.serveItem(nonExistingItemId))
                .isInstanceOf(OrderItemNotFoundException.class);
    }
}
