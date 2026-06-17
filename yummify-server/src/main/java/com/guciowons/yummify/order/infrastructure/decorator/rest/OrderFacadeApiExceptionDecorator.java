package com.guciowons.yummify.order.infrastructure.decorator.rest;

import com.guciowons.yummify.common.exception.infrastructure.in.rest.annotation.HandleDomainExceptions;
import com.guciowons.yummify.order.application.port.OrderFacadePort;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.infrastructure.in.rest.exception.OrderDomainExceptionMapper;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class OrderFacadeApiExceptionDecorator implements OrderFacadePort {
    private final OrderFacadePort delegate;

    @Override
    @HandleDomainExceptions(handler = OrderDomainExceptionMapper.class)
    public Order create(UUID restaurantId, UUID tableId) {
        return delegate.create(restaurantId, tableId);
    }
}
