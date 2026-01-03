package com.guciowons.yummify.dish.application;

import com.guciowons.yummify.dish.application.dto.DishClientDTO;
import com.guciowons.yummify.dish.application.dto.DishImageUrlDTO;
import com.guciowons.yummify.dish.application.dto.DishManageDTO;
import com.guciowons.yummify.dish.application.dto.mapper.DishMapper;
import com.guciowons.yummify.dish.application.service.DishImageUrlProvider;
import com.guciowons.yummify.dish.application.usecase.*;
import com.guciowons.yummify.dish.domain.entity.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishFacade {
    private final DishCreateUsecase dishCreateUsecase;
    private final DishGetAllUsecase dishGetAllUsecase;
    private final DishGetUsecase dishGetUsecase;
    private final DishUpdateUsecase dishUpdateUsecase;
    private final DishUpdateImageUsecase dishUpdateImageUsecase;
    private final DishImageUrlProvider dishImageUrlProvider;
    private final DishMapper dishMapper;

    public DishManageDTO create(DishManageDTO dto, UUID restaurantId) {
        Dish dish = dishCreateUsecase.create(dto, restaurantId);
        return dishMapper.mapToManageDTO(dish, imageId -> dishImageUrlProvider.get(imageId, restaurantId));
    }

    public List<DishClientDTO> getAll(UUID restaurantId) {
        return dishGetAllUsecase.getAll(restaurantId).stream()
                .map(dish -> dishMapper.mapToClientDTO(dish, imageId -> dishImageUrlProvider.get(imageId, restaurantId)))
                .toList();
    }

    public DishManageDTO getManageDTO(UUID id, UUID restaurantId) {
        Dish dish = dishGetUsecase.getById(id, restaurantId);
        return dishMapper.mapToManageDTO(dish, imageId -> dishImageUrlProvider.get(imageId, restaurantId));
    }

    public DishManageDTO update(UUID id, DishManageDTO dto, UUID restaurantId) {
        Dish dish = dishUpdateUsecase.update(id, dto, restaurantId);
        return dishMapper.mapToManageDTO(dish, imageId -> dishImageUrlProvider.get(imageId, restaurantId));
    }

    public DishImageUrlDTO updateImage(UUID id, MultipartFile image, UUID restaurantId) {
        UUID imageId = dishUpdateImageUsecase.updateImage(id, image, restaurantId);
        String url = dishImageUrlProvider.get(imageId, restaurantId);
        return new DishImageUrlDTO(url);
    }
}
