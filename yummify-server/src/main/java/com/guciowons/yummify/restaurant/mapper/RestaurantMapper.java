package com.guciowons.yummify.restaurant.mapper;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.restaurant.dto.RestaurantCreateDTO;
import com.guciowons.yummify.restaurant.dto.RestaurantDTO;
import com.guciowons.yummify.restaurant.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    @Mapping(target = "description", expression = "java(entity.getDescription().get())")
    RestaurantDTO<String> mapToClientDTO(Restaurant entity);

    RestaurantDTO<TranslatedStringDTO> mapToAdminDTO(Restaurant entity);

    @Mapping(target = "id", ignore = true)
    Restaurant mapToEntity(RestaurantCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    Restaurant mapToUpdateEntity(RestaurantDTO<TranslatedStringDTO> dto, @MappingTarget Restaurant entity);
}
