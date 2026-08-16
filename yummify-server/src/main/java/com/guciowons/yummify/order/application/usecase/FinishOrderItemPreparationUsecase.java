package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.FinishOrderItemPreparationCommand;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class FinishOrderItemPreparationUsecase {
    private final OrderLookupService orderLookupService;
    private final OrderRepository orderRepository;

    public OrderItem finishPreparation(FinishOrderItemPreparationCommand command) {
        Order order = orderLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());
        OrderItem item = order.finishItemPreparation(command.itemId());

        orderRepository.save(order);
        return item;
    }
}
