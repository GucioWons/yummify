package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.SubmitOrderCommand;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class SubmitOrderUsecase {
    private final OrderLookupService orderLookupService;
    private final OrderRepository orderRepository;

    public Order submit(SubmitOrderCommand command) {
        Order order = orderLookupService.getByUserIdAndRestaurantId(command.userId(), command.restaurantId());
        order.submit();
        orderRepository.save(order);
        return order;
    }
}
