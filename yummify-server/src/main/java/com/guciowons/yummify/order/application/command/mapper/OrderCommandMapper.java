package com.guciowons.yummify.order.application.command.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.order.application.command.AddOrderItemCommand;
import com.guciowons.yummify.order.application.command.CreateOrderCommand;
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
    CreateOrderCommand toCreateOrderCommand(UUID restaurantId, UUID tableId);

    AddOrderItemCommand toAddOrderItemCommand(UUID id, UUID restaurantId, UUID dishId, int quantity);

    default Order.Id toId(UUID id) {
        return Order.Id.of(id);
    }

    default Order.RestaurantId toRestaurantId(UUID restaurantId) {
        return Order.RestaurantId.of(restaurantId);
    }

    default Order.TableId toTableId(UUID tableId) {
        return Order.TableId.of(tableId);
    }

    default OrderItem.DishId toDishId(UUID dishId) {
        return OrderItem.DishId.of(dishId);
    }
}
