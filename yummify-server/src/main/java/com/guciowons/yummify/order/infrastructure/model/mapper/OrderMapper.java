package com.guciowons.yummify.order.infrastructure.model.mapper;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.infrastructure.model.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "tableId", source = "tableId.value")
    OrderDto toDto(Order order);
}
