package com.guciowons.yummify.dish.infrastructure.out.jpa.entity.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.jpa.JpaTranslatedStringMapper;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.infrastructure.out.jpa.entity.JpaDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = JpaTranslatedStringMapper.class)
public interface JpaDishMapper {
    Dish toDomain(JpaDish jpaDish);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "restaurantId", source = "restaurantId.value")
    @Mapping(target = "imageId", source = "imageId.value")
    JpaDish toJpa(Dish dish);

    default Dish.Id toId(UUID id) {
        return new Dish.Id(id);
    }

    default Dish.RestaurantId toRestaurantId(UUID id) {
        return new Dish.RestaurantId(id);
    }

    default Dish.ImageId toImageId(UUID id) {
        return new Dish.ImageId(id);
    }
}
