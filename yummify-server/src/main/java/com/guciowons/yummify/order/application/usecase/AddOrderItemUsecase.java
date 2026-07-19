package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.AddOrderItemCommand;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class AddOrderItemUsecase {
    private final OrderLookupService orderLookupService;

    public OrderItem addItem(AddOrderItemCommand command) {
        Order order = orderLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());

        return order.addItem(
                command.dishId(),
                OrderItem.DishSnapshot.of(command.nameSnapshot(), command.priceSnapshot()),
                command.quantity()
        );
    }
}
