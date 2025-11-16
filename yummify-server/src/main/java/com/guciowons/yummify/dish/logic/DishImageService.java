package com.guciowons.yummify.dish.logic;

import com.guciowons.yummify.dish.data.DishRepository;
import com.guciowons.yummify.dish.dto.DishImageUrlDTO;
import com.guciowons.yummify.dish.entity.Dish;
import com.guciowons.yummify.file.PublicFileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DishImageService {
    private final DishService dishService;
    private final PublicFileService publicFileService;
    private final DishRepository dishRepository;

    @Transactional
    public DishImageUrlDTO update(UUID id, MultipartFile image) {
        Dish dish = dishService.getEntityById(id);

        if (image.isEmpty()) {
            if (dish.getImageId() != null) {
                publicFileService.delete(dish.getImageId());
                dish.setImageId(null);
                return new DishImageUrlDTO(null);
            }
        } else {
            if (dish.getImageId() == null) {
                dish.setImageId(publicFileService.create("dish", image));
            } else {
                publicFileService.update(dish.getImageId(), "dish", image);
            }
        }

        dishRepository.save(dish);
        return new DishImageUrlDTO(publicFileService.getPresignedUrl(dish.getImageId()));
    }
}
