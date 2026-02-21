package com.guciowons.yummify.restaurant.infrastructure.out.jpa.entity.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.jpa.JpaTranslatedStringMapper;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.infrastructure.out.jpa.entity.JpaRestaurant;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = JpaTranslatedStringMapper.class
)
public interface JpaRestaurantMapper {
    Restaurant toDomain(JpaRestaurant jpaRestaurant);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "ownerId", source = "ownerId.value")
    @Mapping(target = "name", source = "name.value")
    JpaRestaurant toJpa(Restaurant restaurant);

    default Restaurant.Id toId(UUID id) {
        return Restaurant.Id.of(id);
    }

    default Restaurant.OwnerId toOwnerId(UUID id) {
        return Restaurant.OwnerId.of(id);
    }

    default Restaurant.Name toName(String name) {
        return Restaurant.Name.of(name);
    }
}
