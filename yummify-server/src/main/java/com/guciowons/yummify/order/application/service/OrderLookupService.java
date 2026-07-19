package com.guciowons.yummify.order.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.exception.OrderNotFoundException;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class OrderLookupService {
    private final OrderRepository orderRepository;

    public Order getByIdAndRestaurantId(Order.Id id, Order.RestaurantId restaurantId) {
        return orderRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}
