package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.RemoveOrderItemCommand;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.event.OrderUpdatedEvent;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@Usecase
@RequiredArgsConstructor
public class RemoveOrderItemUsecase {
    private final OrderLookupService orderLookupService;
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public void removeOrderItem(RemoveOrderItemCommand command) {
        Order order = orderLookupService.getActiveByUserIdAndRestaurantId(command.userId(), command.restaurantId());
        order.removeItem(command.itemId());

        orderRepository.save(order);

        applicationEventPublisher.publishEvent(OrderUpdatedEvent.of(order, command.userId()));
    }
}
