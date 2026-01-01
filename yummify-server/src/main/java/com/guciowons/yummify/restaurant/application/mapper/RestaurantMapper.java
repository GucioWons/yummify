package com.guciowons.yummify.restaurant.application.mapper;

import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.restaurant.application.dto.RestaurantClientDTO;
import com.guciowons.yummify.restaurant.application.dto.RestaurantManageDTO;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = TranslatedStringMapper.class
)
public interface RestaurantMapper {
    RestaurantManageDTO mapToManageDTO(Restaurant entity);

    RestaurantClientDTO mapToClientDTO(Restaurant entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    Restaurant mapToSaveEntity(RestaurantManageDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    Restaurant mapToUpdateEntity(RestaurantManageDTO dto, @MappingTarget Restaurant entity);
}
