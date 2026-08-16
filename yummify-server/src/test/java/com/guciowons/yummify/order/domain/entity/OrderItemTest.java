package com.guciowons.yummify.order.domain.entity;

import com.guciowons.yummify.order.domain.exception.InvalidOrderItemStatusTransitionException;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void shouldStartPreparationWhenStatusIsNew() {
        // given
        var orderItem = givenOrderItem(1);

        // when
        orderItem.startPreparation();

        // then
        assertThat(orderItem.getStatus()).isEqualTo(OrderItemStatus.IN_PREPARATION);
    }

    @Test
    void shouldNotStartPreparationWhenStatusIsNotNew() {
        // given
        var orderItem = givenOrderItem(1);
        orderItem.startPreparation();

        // when + then
        assertThatThrownBy(orderItem::startPreparation).isInstanceOf(InvalidOrderItemStatusTransitionException.class);
    }

    @Test
    void shouldFinishPreparationWhenStatusIsInPreparation() {
        // given
        var orderItem = givenOrderItem(1);
        orderItem.startPreparation();

        // when
        orderItem.finishPreparation();

        // then
        assertThat(orderItem.getStatus()).isEqualTo(OrderItemStatus.READY);
    }

    @Test
    void shouldFinishPreparationWhenStatusIsNotInPreparation() {
        // given
        var orderItem = givenOrderItem(1);

        // when + then
        assertThatThrownBy(orderItem::finishPreparation).isInstanceOf(InvalidOrderItemStatusTransitionException.class);
    }

    @Test
    void shouldServeWhenStatusIsReady() {
        // given
        var orderItem = givenOrderItem(1);
        orderItem.startPreparation();
        orderItem.finishPreparation();

        // when
        orderItem.serve();

        // then
        assertThat(orderItem.getStatus()).isEqualTo(OrderItemStatus.DELIVERED);
    }

    @Test
    void shouldNotServeAndThrowExceptionWhenStatusIsNotReady() {
        // given
        var orderItem = givenOrderItem(1);
        // when + then
        assertThatThrownBy(orderItem::serve).isInstanceOf(InvalidOrderItemStatusTransitionException.class);
    }

    @Test
    void shouldCancelWhenStatusIsNotDelivered() {
        // given
        var orderItem = givenOrderItem(1);

        // when
        orderItem.cancel();

        // then
        assertThat(orderItem.getStatus()).isEqualTo(OrderItemStatus.CANCELLED);
    }

    @Test
    void shouldNotCancelWhenStatusIsDelivered() {
        // given
        var orderItem = givenOrderItem(1);
        orderItem.startPreparation();
        orderItem.finishPreparation();
        orderItem.serve();

        // when + then
        assertThatThrownBy(orderItem::serve).isInstanceOf(InvalidOrderItemStatusTransitionException.class);
    }

    @Test
    void shouldReturnTrueWhenStatusIsDelivered() {
        // given
        var orderItem = givenOrderItem(1);
        orderItem.startPreparation();
        orderItem.finishPreparation();
        orderItem.serve();

        // when
        var result = orderItem.isDelivered();

        // when
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalseWhenStatusIsNotDelivered() {
        // given
        var orderItem = givenOrderItem(1);

        // when
        var result = orderItem.isDelivered();

        // then
        assertThat(result).isFalse();
    }
}
