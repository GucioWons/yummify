package com.guciowons.yummify.order.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.order.application.command.*;
import com.guciowons.yummify.order.application.command.mapper.OrderCommandMapper;
import com.guciowons.yummify.order.application.port.OrderFacadePort;
import com.guciowons.yummify.order.application.usecase.*;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class OrderFacade implements OrderFacadePort {
    private final CreateOrderUsecase createOrderUsecase;
    private final AddOrderItemUsecase addOrderItemUsecase;
    private final RemoveOrderItemUsecase removeOrderItemUsecase;
    private final SubmitOrderUsecase submitOrderUsecase;
    private final CancelOrderUsecase cancelOrderUsecase;
    private final StartOrderItemPreparationUsecase startOrderItemPreparationUsecase;
    private final FinishOrderItemPreparationUsecase finishOrderItemPreparationUsecase;
    private final ServeOrderItemUsecase serveOrderItemUsecase;
    private final RequestAssistanceUsecase requestAssistanceUsecase;
    private final OrderCommandMapper orderCommandMapper;

    @Override
    public Order create(UUID userId, UUID restaurantId) {
        CreateOrderCommand command = orderCommandMapper.toCreateOrderCommand(userId, restaurantId);
        return createOrderUsecase.create(command);
    }

    @Override
    public OrderItem addItem(UUID userId, UUID restaurantId, UUID dishId, int quantity) {
        AddOrderItemCommand command = orderCommandMapper.toAddOrderItemCommand(userId, restaurantId, dishId, quantity);
        return addOrderItemUsecase.addItem(command);
    }

    @Override
    public void removeItem(UUID userId, UUID restaurantId, UUID orderItemId) {
        RemoveOrderItemCommand command = orderCommandMapper.toRemoveOrderItemCommand(userId, restaurantId, orderItemId);
        removeOrderItemUsecase.removeOrderItem(command);
    }

    @Override
    public Order submit(UUID userId, UUID restaurantId) {
        SubmitOrderCommand command = orderCommandMapper.toSubmitOrderCommand(userId, restaurantId);
        return submitOrderUsecase.submit(command);
    }

    @Override
    public Order cancel(UUID userId, UUID restaurantId) {
        CancelOrderCommand command = orderCommandMapper.toCancelOrderCommand(userId, restaurantId);
        return cancelOrderUsecase.cancel(command);
    }

    @Override
    public OrderItem startPreparation(UUID orderId, UUID restaurantId, UUID itemId) {
        StartOrderItemPreparationCommand command = orderCommandMapper.toStartOrderItemPreparationCommand(
                orderId,
                restaurantId,
                itemId
        );
        return startOrderItemPreparationUsecase.startPreparation(command);
    }

    @Override
    public OrderItem finishPreparation(UUID orderId, UUID restaurantId, UUID itemId) {
        FinishOrderItemPreparationCommand command = orderCommandMapper.toFinishOrderItemPreparationCommand(
                orderId,
                restaurantId,
                itemId
        );
        return finishOrderItemPreparationUsecase.finishPreparation(command);
    }

    @Override
    public OrderItem serve(UUID orderId, UUID restaurantId, UUID itemId) {
        ServeOrderItemCommand command = orderCommandMapper.toServeOrderItemCommand(orderId, restaurantId, itemId);
        return serveOrderItemUsecase.serve(command);
    }

    @Override
    public Order requestAssistance(UUID userId, UUID restaurantId) {
        RequestAssistanceCommand command = orderCommandMapper.toRequestAssistanceCommand(userId, restaurantId);
        return requestAssistanceUsecase.request(command);
    }
}
