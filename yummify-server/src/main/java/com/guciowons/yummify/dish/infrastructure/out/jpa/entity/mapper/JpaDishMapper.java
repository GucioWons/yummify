package com.guciowons.yummify.dish.infrastructure.out.jpa.entity.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.jpa.JpaTranslatedStringMapper;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.infrastructure.out.jpa.entity.JpaDish;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = JpaTranslatedStringMapper.class
)
public interface JpaDishMapper {
    @Mapping(target = "imageId", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
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
        return id != null ? new Dish.ImageId(id) : null;
    }
}
