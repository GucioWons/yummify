package com.guciowons.yummify.order.domain.port.out;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order);

    Optional<Order> findByIdAndRestaurantId(Order.Id id, Order.RestaurantId restaurantId);

    Optional<Order> findByTableIdAndRestaurantId(Order.TableId id, Order.RestaurantId restaurantId);

    List<Order> findAllByStatusInAndRestaurantId(List<OrderStatus> statuses, Order.RestaurantId restaurantId);
}
