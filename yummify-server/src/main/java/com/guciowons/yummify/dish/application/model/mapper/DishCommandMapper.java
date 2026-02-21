package com.guciowons.yummify.dish.application.model.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.dish.application.model.*;
import com.guciowons.yummify.dish.domain.entity.Dish;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = TranslatedStringMapper.class
)
public interface DishCommandMapper {
    @Mapping(target = "restaurantId", source = "restaurantId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "ingredientIds", source = "ingredientIds")
    CreateDishCommand toCreateDishCommand(
            UUID restaurantId,
            Map<String, String> name,
            Map<String, String> description,
            List<UUID> ingredientIds
    );

    GetAllDishesQuery toGetAllDishesCommand(UUID restaurantId);

    GetDishQuery toGetDishCommand(UUID id, UUID restaurantId);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "restaurantId", source = "restaurantId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "ingredientIds", source = "ingredientIds")
    UpdateDishCommand toUpdateDishCommand(
            UUID id,
            UUID restaurantId,
            Map<String, String> name,
            Map<String, String> description,
            List<UUID> ingredientIds
    );

    UpdateDishImageCommand toUpdateDishImageCommand(UUID id, MultipartFile image, UUID restaurantId);

    default Dish.Id toId(UUID id) {
        return Dish.Id.of(id);
    }

    default Dish.RestaurantId toRestaurantId(UUID id) {
        return Dish.RestaurantId.of(id);
    }
}
