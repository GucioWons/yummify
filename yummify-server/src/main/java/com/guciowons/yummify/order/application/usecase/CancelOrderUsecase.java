package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.CancelOrderCommand;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.event.OrderUpdatedEvent;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@Usecase
@RequiredArgsConstructor
public class CancelOrderUsecase {
    private final OrderLookupService orderLookupService;
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Order cancel(CancelOrderCommand command) {
        Order order = orderLookupService.getActiveByUserIdAndRestaurantId(command.userId(), command.restaurantId());
        order.cancel();

        orderRepository.save(order);

        applicationEventPublisher.publishEvent(OrderUpdatedEvent.of(order));

        return order;
    }
}
