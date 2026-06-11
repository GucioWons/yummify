package com.guciowons.yummify.order.domain.port.out;

import com.guciowons.yummify.order.domain.entity.Order;

public interface OrderRepository {
    void save(Order order);
}
