package com.guciowons.yummify.restaurant.application.model.mapper;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.restaurant.application.model.CreateRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.GetRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.RestaurantOwner;
import com.guciowons.yummify.restaurant.application.model.UpdateRestaurantCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RestaurantCommandMapper {
    @Mapping(target = "name", expression = "java(RestaurantName.of(name))")
    CreateRestaurantCommand toCreateRestaurantCommand(
            String name,
            TranslatedString description,
            Language defaultLanguage,
            RestaurantOwner owner
    );

    @Mapping(target = "id", expression = "java(RestaurantId.of(id))")
    @Mapping(target = "name", expression = "java(RestaurantName.of(name))")
    UpdateRestaurantCommand toUpdateRestaurantCommand(
            UUID id,
            String name,
            TranslatedString description,
            Language language
    );

    @Mapping(target = "id", expression = "java(RestaurantId.of(id))")
    GetRestaurantCommand toGetRestaurantCommand(UUID id);
}
