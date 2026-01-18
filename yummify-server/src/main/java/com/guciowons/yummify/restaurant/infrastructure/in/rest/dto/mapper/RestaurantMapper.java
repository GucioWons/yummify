package com.guciowons.yummify.restaurant.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.dto.RestaurantClientDTO;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.dto.RestaurantManageDTO;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.dto.RestaurantOwnerDTO;
import com.guciowons.yummify.restaurant.application.model.RestaurantOwner;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = TranslatedStringMapper.class
)
public interface RestaurantMapper {
    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "name", source = "name.value")
    RestaurantManageDTO toManageDTO(Restaurant entity);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "name", source = "name.value")
    RestaurantClientDTO toClientDTO(Restaurant entity);

    RestaurantOwner toOwner(RestaurantOwnerDTO dto);
}
