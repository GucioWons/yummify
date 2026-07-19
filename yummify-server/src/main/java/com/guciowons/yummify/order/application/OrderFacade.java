package com.guciowons.yummify.order.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.order.application.command.AddOrderItemCommand;
import com.guciowons.yummify.order.application.command.CreateOrderCommand;
import com.guciowons.yummify.order.application.command.mapper.OrderCommandMapper;
import com.guciowons.yummify.order.application.port.OrderFacadePort;
import com.guciowons.yummify.order.application.usecase.AddOrderItemUsecase;
import com.guciowons.yummify.order.application.usecase.CreateOrderUsecase;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class OrderFacade implements OrderFacadePort {
    private final CreateOrderUsecase createOrderUsecase;
    private final AddOrderItemUsecase addOrderItemUsecase;
    private final OrderCommandMapper orderCommandMapper;

    @Override
    public Order create(UUID restaurantId, UUID tableId) {
        CreateOrderCommand command = orderCommandMapper.toCreateOrderCommand(restaurantId, tableId);
        return createOrderUsecase.create(command);
    }

    @Override
    public OrderItem addItem(
            UUID orderId,
            UUID restaurantId,
            UUID dishId,
            int quantity
    ) {
        AddOrderItemCommand command = orderCommandMapper.toAddOrderItemCommand(orderId, restaurantId, dishId, quantity);
        return addOrderItemUsecase.addItem(command);
    }
}
