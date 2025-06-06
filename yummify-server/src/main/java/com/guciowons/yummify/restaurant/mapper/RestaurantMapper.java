package com.guciowons.yummify.restaurant.mapper;

import com.guciowons.yummify.restaurant.RestaurantDTO;
import com.guciowons.yummify.restaurant.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDTO mapToDTO(Restaurant entity);

    @Mapping(target = "id", ignore = true)
    Restaurant mapToEntity(RestaurantDTO dto);

    @Mapping(target = "id", ignore = true)
    Restaurant mapToUpdateEntity(RestaurantDTO dto, @MappingTarget Restaurant entity);
}
