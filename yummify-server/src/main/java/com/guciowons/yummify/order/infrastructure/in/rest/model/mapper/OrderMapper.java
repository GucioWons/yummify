package com.guciowons.yummify.order.infrastructure.in.rest.model.mapper;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.infrastructure.in.rest.model.OrderClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "tableId", source = "tableId.value")
    OrderClientDto toClientDto(Order order);
}
