package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.dish.PublicDishFacadePort;
import com.guciowons.yummify.menu.PublicMenuFacadePort;
import com.guciowons.yummify.order.application.command.AddOrderItemCommand;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import com.guciowons.yummify.order.domain.event.OrderUpdatedEvent;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@Usecase
@RequiredArgsConstructor
public class AddOrderItemUsecase {
    private final OrderLookupService orderLookupService;
    private final PublicDishFacadePort publicDishFacadePort;
    private final PublicMenuFacadePort publicMenuFacadePort;
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderItem addItem(AddOrderItemCommand command) {
        Order order = orderLookupService.getActiveByUserIdAndRestaurantId(command.userId(), command.restaurantId());

        OrderItem.DishSnapshot dishSnapshot = OrderItem.DishSnapshot.of(
                publicDishFacadePort.get(command.dishId().value(), command.restaurantId().value()).name(),
                publicMenuFacadePort.getPriceByDishId(command.restaurantId().value(), command.dishId().value())
        );

        OrderItem item = order.addItem(command.dishId(), dishSnapshot, command.quantity());
        orderRepository.save(order);

        applicationEventPublisher.publishEvent(OrderUpdatedEvent.of(order));

        return item;
    }
}
