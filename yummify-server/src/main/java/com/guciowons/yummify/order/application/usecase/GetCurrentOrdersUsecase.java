package com.guciowons.yummify.order.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.order.application.command.GetOrdersQuery;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderStatus;
import com.guciowons.yummify.order.domain.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Usecase
@RequiredArgsConstructor
public class GetCurrentOrdersUsecase {
    private final OrderRepository orderRepository;

    public List<Order> get(GetOrdersQuery query) {
        return orderRepository.findAllByStatusInAndRestaurantId(OrderStatus.CURRENT_STATUSES, query.restaurantId());
    }
}
