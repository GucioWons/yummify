package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.CreateOrderCommand;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateOrderUsecase {
    private final OrderRepository orderRepository;

    public Order create(CreateOrderCommand command) {
        Order order = Order.create(command.restaurantId(), command.tableId());
        orderRepository.save(order);
        return order;
    }
}
