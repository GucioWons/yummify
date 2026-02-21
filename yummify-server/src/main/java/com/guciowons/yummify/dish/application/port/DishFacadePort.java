package com.guciowons.yummify.dish.application.port;

import com.guciowons.yummify.dish.domain.entity.Dish;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DishFacadePort {
    Dish create(UUID restaurantId, Map<String, String> name, Map<String, String> description, List<UUID> ingredientIds);

    List<Dish> getAll(UUID restaurantId);

    Dish getById(UUID id, UUID restaurantId);

    Dish update(UUID id, UUID restaurantId, Map<String, String> name, Map<String, String> description, List<UUID> ingredientIds);

    Dish.ImageId updateImage(UUID id, UUID restaurantId, MultipartFile image);
}
