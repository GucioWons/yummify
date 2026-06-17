package com.guciowons.yummify.order.infrastructure.out.jpa.entity.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.jpa.JpaTranslatedStringMapper;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import com.guciowons.yummify.order.infrastructure.out.jpa.entity.JpaOrder;
import com.guciowons.yummify.order.infrastructure.out.jpa.entity.JpaOrderItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = JpaTranslatedStringMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface JpaOrderMapper {
    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "restaurantId.value", source = "restaurantId")
    @Mapping(target = "tableId.value", source = "tableId")
    Order toDomain(JpaOrder jpaOrder);

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "dishSnapshot.name", source = "nameSnapshot")
    @Mapping(target = "dishSnapshot.price", source = "price")
    @Mapping(target = "dishId.value", source = "dishId")
    OrderItem toDomain(JpaOrderItem jpaOrderItem);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "restaurantId", source = "restaurantId.value")
    @Mapping(target = "tableId", source = "tableId.value")
    JpaOrder toJpa(Order order);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "dishId", source = "dishId.value")
    @Mapping(target = "nameSnapshot", source = "dishSnapshot.name")
    @Mapping(target = "price", source = "dishSnapshot.price")
    JpaOrderItem toJpa(OrderItem orderItem);
}
