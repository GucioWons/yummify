package com.guciowons.yummify.order.domain.port.out;

import com.guciowons.yummify.order.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {
    void save(Order order);

    Optional<Order> findByIdAndRestaurantId(Order.Id id, Order.RestaurantId restaurantId);
}
