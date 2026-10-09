package com.guciowons.yummify.dish.infrastructure.out.s3;

import com.guciowons.yummify.common.file.domain.model.File;
import com.guciowons.yummify.dish.application.port.DishImageManagementPort;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.common.file.application.FileStoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3DishImageManagementAdapter implements DishImageManagementPort {
    private static final String DIRECTORY = "dishes";

    private final FileStoragePort fileStoragePort;

    @Override
    public File create(Dish.RestaurantId restaurantId, MultipartFile content) {
        File image = File.create(buildStorageKey(restaurantId, content.getOriginalFilename()));

        fileStoragePort.store(image.getStorageKey(), content);

        return image;
    }

    @Override
    public void update(File image, Dish.RestaurantId restaurantId, MultipartFile content) {
        File.StorageKey oldStorageKey = image.getStorageKey();
        File.StorageKey newStorageKey = buildStorageKey(restaurantId, content.getOriginalFilename());

        fileStoragePort.store(newStorageKey, content);

        try {
            image.changeStorageKey(newStorageKey);
            fileStoragePort.remove(oldStorageKey);
        } catch (Exception e) {
            fileStoragePort.remove(newStorageKey);
            throw e;
        }
    }

    @Override
    public void delete(File image) {
        fileStoragePort.remove(image.getStorageKey());
    }

    public File.StorageKey buildStorageKey(Dish.RestaurantId restaurantId, String filename) {
        return new File.StorageKey("%s/%s/%s-%s".formatted(restaurantId.value(), DIRECTORY, UUID.randomUUID(), filename));
    }
}
