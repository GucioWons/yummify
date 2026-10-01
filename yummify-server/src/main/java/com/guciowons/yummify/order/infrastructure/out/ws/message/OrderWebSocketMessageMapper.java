package com.guciowons.yummify.order.infrastructure.out.ws.message;

import com.guciowons.yummify.order.domain.event.OrderCreatedEvent;
import com.guciowons.yummify.order.domain.event.OrderUpdatedEvent;
import com.guciowons.yummify.order.infrastructure.model.mapper.OrderMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = OrderMapper.class)
public interface OrderWebSocketMessageMapper {
    @Mapping(target = "type", expression = "java(OrderWebSocketMessage.Type.CREATED)")
    OrderWebSocketMessage map(OrderCreatedEvent event);

    @Mapping(target = "type", expression = "java(OrderWebSocketMessage.Type.UPDATED)")
    OrderWebSocketMessage map(OrderUpdatedEvent event);
}
