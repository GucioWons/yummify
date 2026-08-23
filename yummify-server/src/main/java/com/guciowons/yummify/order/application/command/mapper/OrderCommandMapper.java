package com.guciowons.yummify.order.application.command.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.order.application.command.*;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = TranslatedStringMapper.class
)
public interface OrderCommandMapper {
    CreateOrderCommand toCreateOrderCommand(UUID userId, UUID restaurantId);

    AddOrderItemCommand toAddOrderItemCommand(UUID userId, UUID restaurantId, UUID dishId, int quantity);

    RemoveOrderItemCommand toRemoveOrderItemCommand(UUID userId, UUID restaurantId, UUID itemId);

    SubmitOrderCommand toSubmitOrderCommand(UUID userId, UUID restaurantId);

    CancelOrderCommand toCancelOrderCommand(UUID id, UUID restaurantId);

    StartOrderItemPreparationCommand toStartOrderItemPreparationCommand(UUID id, UUID restaurantId, UUID itemId);

    FinishOrderItemPreparationCommand toFinishOrderItemPreparationCommand(UUID id, UUID restaurantId, UUID itemId);

    ServeOrderItemCommand toServeOrderItemCommand(UUID id, UUID restaurantId, UUID itemId);

    RequestAssistanceCommand toRequestAssistanceCommand(UUID userId, UUID restaurantId);

    RequestPaymentCommand toRequestPaymentCommand(UUID userId, UUID restaurantId);

    default Order.Id toId(UUID id) {
        return Order.Id.of(id);
    }

    default Order.RestaurantId toRestaurantId(UUID restaurantId) {
        return Order.RestaurantId.of(restaurantId);
    }

    default OrderItem.Id toItemId(UUID itemId) {
        return OrderItem.Id.of(itemId);
    }

    default OrderItem.DishId toDishId(UUID dishId) {
        return OrderItem.DishId.of(dishId);
    }
}
