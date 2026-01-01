package com.guciowons.yummify.dish.domain.logic;

import com.guciowons.yummify.dish.infractructure.entity.Dish;
import com.guciowons.yummify.file.PublicFileFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class DishUpdateImageService {
    private final PublicFileFacade publicFileFacade;

    public void updateImage(Dish dish, MultipartFile image) {
        if (image.isEmpty()) {
            if (dish.getImageId() != null) {
                publicFileFacade.delete(dish.getImageId());
                dish.setImageId(null);
            }
        } else {
            if (dish.getImageId() == null) {
                dish.setImageId(publicFileFacade.create("dish", image));
            } else {
                publicFileFacade.update(dish.getImageId(), "dish", image);
            }
        }
    }
}
