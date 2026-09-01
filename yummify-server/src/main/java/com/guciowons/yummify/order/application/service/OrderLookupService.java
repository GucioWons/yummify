package com.guciowons.yummify.order.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.exception.OrderNotFoundException;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import com.guciowons.yummify.table.PublicTableFacadePort;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@ApplicationService
@RequiredArgsConstructor
public class OrderLookupService {
    private final PublicTableFacadePort publicTableFacadePort;
    private final OrderRepository orderRepository;

    public Order getByIdAndRestaurantId(Order.Id id, Order.RestaurantId restaurantId) {
        return orderRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> OrderNotFoundException.byId(id));
    }

    public Order getByUserIdAndRestaurantId(UUID userId, Order.RestaurantId restaurantId) {
        Order.TableId tableId = Order.TableId.of(publicTableFacadePort.getTableIdByUserId(userId, restaurantId.value()));

        return orderRepository.findByTableIdAndRestaurantId(tableId, restaurantId)
                .orElseThrow(() -> OrderNotFoundException.byTableId(tableId));
    }
}
