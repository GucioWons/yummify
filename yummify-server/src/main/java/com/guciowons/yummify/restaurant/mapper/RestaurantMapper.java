package com.guciowons.yummify.restaurant.mapper;

import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.restaurant.dto.RestaurantClientDTO;
import com.guciowons.yummify.restaurant.dto.RestaurantManageDTO;
import com.guciowons.yummify.restaurant.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = TranslatedStringMapper.class)
public interface RestaurantMapper {
    RestaurantClientDTO mapToClientDTO(Restaurant entity);

    RestaurantManageDTO mapToAdminDTO(Restaurant entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    Restaurant mapToEntity(RestaurantManageDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    Restaurant mapToUpdateEntity(RestaurantManageDTO dto, @MappingTarget Restaurant entity);
}
