package com.guciowons.yummify.order.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.order.application.command.CreateOrderCommand;
import com.guciowons.yummify.order.application.command.mapper.OrderCommandMapper;
import com.guciowons.yummify.order.application.usecase.CreateOrderUsecase;
import com.guciowons.yummify.order.domain.entity.Order;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class OrderFacade {
    private final CreateOrderUsecase createOrderUsecase;
    private final OrderCommandMapper orderCommandMapper;

    public Order create(UUID restaurantId, UUID tableId) {
        CreateOrderCommand command = orderCommandMapper.toCreateOrderCommand(restaurantId, tableId);
        return createOrderUsecase.create(command);
    }
}
