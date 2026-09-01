package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.RequestPaymentCommand;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class RequestPaymentUsecase {
    private final OrderLookupService orderLookupService;
    private final OrderRepository orderRepository;

    public Order request(RequestPaymentCommand command) {
        Order order = orderLookupService.getByUserIdAndRestaurantId(command.userId(), command.restaurantId());
        order.requestPayment();

        orderRepository.save(order);
        return order;
    }
}
