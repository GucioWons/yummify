package com.guciowons.yummify.restaurant.mapper;

import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.common.core.mapper.TranslatableMapper;
import com.guciowons.yummify.restaurant.dto.RestaurantClientDTO;
import com.guciowons.yummify.restaurant.dto.RestaurantDTO;
import com.guciowons.yummify.restaurant.dto.RestaurantManageDTO;
import com.guciowons.yummify.restaurant.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = TranslatedStringMapper.class)
public interface RestaurantMapper extends TranslatableMapper<Restaurant, RestaurantDTO, RestaurantManageDTO, RestaurantClientDTO> {
    RestaurantManageDTO mapToManageDTO(Restaurant entity);

    RestaurantClientDTO mapToClientDTO(Restaurant entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    Restaurant mapToSaveEntity(RestaurantManageDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    Restaurant mapToUpdateEntity(RestaurantManageDTO dto, @MappingTarget Restaurant entity);
}
