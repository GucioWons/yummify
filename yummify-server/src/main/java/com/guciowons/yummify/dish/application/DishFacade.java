package com.guciowons.yummify.dish.application;

import com.guciowons.yummify.dish.application.dto.DishClientDTO;
import com.guciowons.yummify.dish.application.usecase.*;
import com.guciowons.yummify.dish.application.dto.DishImageUrlDTO;
import com.guciowons.yummify.dish.application.dto.DishManageDTO;
import com.guciowons.yummify.dish.infractructure.entity.Dish;
import com.guciowons.yummify.dish.infractructure.mapper.DishMapper;
import com.guciowons.yummify.file.PublicFileFacade;
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
    private final DishMapper dishMapper;
    private final PublicFileFacade publicFileFacade;

    public DishManageDTO create(DishManageDTO dto) {
        Dish dish = dishCreateUsecase.create(dto);
        return dishMapper.mapToManageDTO(dish);
    }

    public List<DishClientDTO> getAll() {
        return dishGetAllUsecase.getAll().stream()
                .map(dishMapper::mapToClientDTO)
                .toList();
    }

    public DishManageDTO getManageDTO(UUID id) {
        Dish dish = dishGetUsecase.getById(id);
        String imageUrl = getImageUrl(dish.getImageId());
        return dishMapper.mapToManageDTO(dish, imageUrl);
    }

    public DishManageDTO update(UUID id, DishManageDTO dto) {
        Dish dish = dishUpdateUsecase.update(id, dto);
        String imageUrl = getImageUrl(dish.getImageId());
        return dishMapper.mapToManageDTO(dish, imageUrl);
    }

    public DishImageUrlDTO updateImage(UUID id, MultipartFile image) {
        UUID imageId = dishUpdateImageUsecase.updateImage(id, image);
        String url = getImageUrl(imageId);
        return new DishImageUrlDTO(url);
    }

    private String getImageUrl(UUID imageId) {
        if (imageId == null) {
            return null;
        }
        return publicFileFacade.getPresignedUrl(imageId);
    }
}
