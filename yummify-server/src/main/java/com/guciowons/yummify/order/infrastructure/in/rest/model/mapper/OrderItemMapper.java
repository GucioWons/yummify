package com.guciowons.yummify.order.infrastructure.in.rest.model.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import com.guciowons.yummify.order.infrastructure.in.rest.model.OrderItemClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TranslatedStringMapper.class)
public interface OrderItemMapper {
    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "dishId", source = "dishId.value")
    @Mapping(target = "name", source = "dishSnapshot.name")
    @Mapping(target = "price", source = "dishSnapshot.price")
    OrderItemClientDto toOrderItemClientDto(OrderItem orderItem);
}
