package com.guciowons.yummify.order.application.command.mapper;

import com.guciowons.yummify.order.application.command.CreateOrderCommand;
import com.guciowons.yummify.order.domain.entity.Order;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface OrderCommandMapper {
    CreateOrderCommand toCreateOrderCommand(UUID restaurantId, UUID tableId);

    default Order.RestaurantId toRestaurantId(UUID restaurantId) {
        return Order.RestaurantId.of(restaurantId);
    }

    default Order.TableId toTableId(UUID tableId) {
        return Order.TableId.of(tableId);
    }
}
