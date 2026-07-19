package com.guciowons.yummify.order.application;

import com.guciowons.yummify.order.application.command.mapper.OrderCommandMapper;
import com.guciowons.yummify.order.application.usecase.AddOrderItemUsecase;
import com.guciowons.yummify.order.application.usecase.CreateOrderUsecase;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.order.application.fixture.OrderApplicationFixture.givenAddOrderItemCommand;
import static com.guciowons.yummify.order.application.fixture.OrderApplicationFixture.givenCreateOrderCommand;
import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class OrderFacadeTest {
    private final CreateOrderUsecase createOrderUsecase = mock(CreateOrderUsecase.class);
    private final AddOrderItemUsecase addOrderItemUsecase = mock(AddOrderItemUsecase.class);
    private final OrderCommandMapper orderCommandMapper = mock(OrderCommandMapper.class);

    private final OrderFacade underTest = new OrderFacade(createOrderUsecase, addOrderItemUsecase, orderCommandMapper);

    @Test
    void shouldCreateOrder() {
        // given
        var restaurantId = givenOrderRestaurantId(1).value();
        var tableId = givenOrderTableId(1).value();
        var command = givenCreateOrderCommand();
        var order = givenOrder(1);

        when(orderCommandMapper.toCreateOrderCommand(restaurantId, tableId)).thenReturn(command);
        when(createOrderUsecase.create(command)).thenReturn(order);

        // when
        var result = underTest.create(restaurantId, tableId);

        // then
        verify(orderCommandMapper).toCreateOrderCommand(restaurantId, tableId);
        verify(createOrderUsecase).create(command);

        assertThat(result).isEqualTo(order);
    }

    @Test
    void shouldAddOrderItem() {
        // given
        var orderId = givenOrderId(1).value();
        var restaurantId = givenOrderRestaurantId(1).value();
        var dishId = givenOrderItemDishId(1).value();
        var quantity = 1;
        var command = givenAddOrderItemCommand();
        var orderItem = givenOrderItem(1);

        when(orderCommandMapper.toAddOrderItemCommand(orderId, restaurantId, dishId, quantity)).thenReturn(command);
        when(addOrderItemUsecase.addItem(command)).thenReturn(orderItem);

        // when
        var result = underTest.addItem(orderId, restaurantId, dishId, quantity);

        // then
        verify(orderCommandMapper).toAddOrderItemCommand(orderId, restaurantId, dishId, quantity);
        verify(addOrderItemUsecase).addItem(command);

        assertThat(result).isEqualTo(orderItem);
    }
}