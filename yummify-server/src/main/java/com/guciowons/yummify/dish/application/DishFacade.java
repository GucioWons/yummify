package com.guciowons.yummify.dish.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.dish.application.model.*;
import com.guciowons.yummify.dish.application.model.mapper.DishCommandMapper;
import com.guciowons.yummify.dish.application.port.DishFacadePort;
import com.guciowons.yummify.dish.application.usecase.*;
import com.guciowons.yummify.dish.domain.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class DishFacade implements DishFacadePort {
    private final CreateDishUsecase createDishUsecase;
    private final GetAllDishesUsecase getAllDishesUsecase;
    private final GetDishUsecase getDishUsecase;
    private final UpdateDishUsecase updateDishUsecase;
    private final UpdateDishImageUsecase updateDishImageUsecase;
    private final DishCommandMapper dishCommandMapper;

    @Override
    public Dish create(UUID restaurantId, Map<String, String> name, Map<String, String> description, List<UUID> ingredientIds) {
        CreateDishCommand command = dishCommandMapper.toCreateDishCommand(restaurantId, name, description, ingredientIds);
        return createDishUsecase.create(command);
    }

    @Override
    public List<Dish> getAll(UUID restaurantId) {
        return getAllDishesUsecase.getAll(dishCommandMapper.toGetAllDishesCommand(restaurantId));
    }

    @Override
    public Dish getById(UUID id, UUID restaurantId) {
        GetDishQuery query = dishCommandMapper.toGetDishCommand(id, restaurantId);
        return getDishUsecase.getById(query);
    }

    @Override
    public Dish update(UUID id, UUID restaurantId, Map<String, String> name, Map<String, String> description, List<UUID> ingredientIds) {
        UpdateDishCommand command = dishCommandMapper.toUpdateDishCommand(id, restaurantId, name, description, ingredientIds);
        return updateDishUsecase.update(command);
    }

    @Override
    public Dish.ImageId updateImage(UUID id, UUID restaurantId, MultipartFile image) {
        UpdateDishImageCommand command = dishCommandMapper.toUpdateDishImageCommand(id, image, restaurantId);
        return updateDishImageUsecase.updateImage(command);
    }
}
