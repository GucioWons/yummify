package com.guciowons.yummify.dish.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.dish.application.model.*;
import com.guciowons.yummify.dish.application.model.mapper.DishCommandMapper;
import com.guciowons.yummify.dish.application.usecase.*;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.entity.value.DishImageId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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

    public Dish create(UUID restaurantId, TranslatedString name, TranslatedString description, List<UUID> ingredientIds) {
        CreateDishCommand command = dishCommandMapper.toCreateDishCommand(restaurantId, name, description, ingredientIds);
        return dishDomainExceptionHandler.handle(() -> createDishUsecase.create(command));
    }

    public List<Dish> getAll(UUID restaurantId) {
        return getAllDishesUsecase.getAll(dishCommandMapper.toGetAllDishesCommand(restaurantId));
    }

    public Dish getById(UUID id, UUID restaurantId) {
        GetDishCommand command = dishCommandMapper.toGetDishCommand(id, restaurantId);
        return dishDomainExceptionHandler.handle(() -> getDishUsecase.getById(command));
    }

    public Dish update(UUID id, UUID restaurantId, TranslatedString name, TranslatedString description, List<UUID> ingredientIds) {
        UpdateDishCommand command = dishCommandMapper.toUpdateDishCommand(id, restaurantId, name, description, ingredientIds);
        return dishDomainExceptionHandler.handle(() -> updateDishUsecase.update(command));
    }

    public DishImageId updateImage(UUID id, UUID restaurantId, MultipartFile image) {
        UpdateDishImageCommand command = dishCommandMapper.toUpdateDishImageCommand(id, image, restaurantId);
        return dishDomainExceptionHandler.handle(() -> updateDishImageUsecase.updateImage(command));
    }
}
