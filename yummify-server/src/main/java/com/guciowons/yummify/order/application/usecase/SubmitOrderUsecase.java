package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.SubmitOrderCommand;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderStatus;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class SubmitOrderUsecase {
    private final OrderLookupService orderLookupService;

    public Order submit(SubmitOrderCommand command) {
        Order order = orderLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());
        order.updateStatus(OrderStatus.SUBMITTED);
        return order;
    }
}
