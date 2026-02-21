package com.guciowons.yummify.restaurant.application.model.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.restaurant.application.model.CreateRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.GetRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.RestaurantOwner;
import com.guciowons.yummify.restaurant.application.model.UpdateRestaurantCommand;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = TranslatedStringMapper.class
)
public interface RestaurantCommandMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "defaultLanguage", source = "defaultLanguage")
    @Mapping(target = "owner", source = "owner")
    CreateRestaurantCommand toCreateRestaurantCommand(
            String name,
            Map<String, String> description,
            String defaultLanguage,
            RestaurantOwner owner
    );

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "defaultLanguage", source = "defaultLanguage")
    UpdateRestaurantCommand toUpdateRestaurantCommand(
            UUID id,
            String name,
            Map<String, String> description,
            String defaultLanguage
    );

    GetRestaurantCommand toGetRestaurantCommand(UUID id);

    default Restaurant.Id toId(UUID id) {
        return Restaurant.Id.of(id);
    }

    default Restaurant.Name toName(String name) {
        return Restaurant.Name.of(name);
    }
}
