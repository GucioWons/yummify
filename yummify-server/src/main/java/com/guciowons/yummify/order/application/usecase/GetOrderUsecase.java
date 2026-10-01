package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.GetOrderQuery;
import com.guciowons.yummify.order.application.service.OrderLookupService;
import com.guciowons.yummify.order.domain.entity.Order;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GetOrderUsecase {
    private final OrderLookupService orderLookupService;

    public Order get(GetOrderQuery query) {
        return orderLookupService.getActiveByUserIdAndRestaurantId(query.userId(), query.restaurantId());
    }
}
