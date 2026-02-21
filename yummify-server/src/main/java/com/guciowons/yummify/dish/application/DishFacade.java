package com.guciowons.yummify.dish.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.exception.infrastructure.DomainExceptionHandler;
import com.guciowons.yummify.dish.application.model.*;
import com.guciowons.yummify.dish.application.model.mapper.DishCommandMapper;
import com.guciowons.yummify.dish.application.usecase.*;
import com.guciowons.yummify.dish.domain.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class DishFacade {
    private final CreateDishUsecase createDishUsecase;
    private final GetAllDishesUsecase getAllDishesUsecase;
    private final GetDishUsecase getDishUsecase;
    private final UpdateDishUsecase updateDishUsecase;
    private final UpdateDishImageUsecase updateDishImageUsecase;
    private final DomainExceptionHandler dishDomainExceptionHandler;
    private final DishCommandMapper dishCommandMapper;

    public Dish create(UUID restaurantId, Map<String, String> name, Map<String, String> description, List<UUID> ingredientIds) {
        CreateDishCommand command = dishCommandMapper.toCreateDishCommand(restaurantId, name, description, ingredientIds);
        return dishDomainExceptionHandler.handle(() -> createDishUsecase.create(command));
    }

    public List<Dish> getAll(UUID restaurantId) {
        return getAllDishesUsecase.getAll(dishCommandMapper.toGetAllDishesCommand(restaurantId));
    }

    public Dish getById(UUID id, UUID restaurantId) {
        GetDishQuery query = dishCommandMapper.toGetDishCommand(id, restaurantId);
        return dishDomainExceptionHandler.handle(() -> getDishUsecase.getById(query));
    }

    public Dish update(UUID id, UUID restaurantId, Map<String, String> name, Map<String, String> description, List<UUID> ingredientIds) {
        UpdateDishCommand command = dishCommandMapper.toUpdateDishCommand(id, restaurantId, name, description, ingredientIds);
        return dishDomainExceptionHandler.handle(() -> updateDishUsecase.update(command));
    }

    public Dish.ImageId updateImage(UUID id, UUID restaurantId, MultipartFile image) {
        UpdateDishImageCommand command = dishCommandMapper.toUpdateDishImageCommand(id, image, restaurantId);
        return dishDomainExceptionHandler.handle(() -> updateDishImageUsecase.updateImage(command));
    }
}
