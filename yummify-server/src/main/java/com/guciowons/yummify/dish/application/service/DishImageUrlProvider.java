package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.file.exposed.FileFacadePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DishImageUrlProvider {
    private final FileFacadePort fileFacadePort;

    public String get(UUID imageId, UUID restaurantId) {
        return imageId != null
                ? fileFacadePort.getUrl(imageId, restaurantId)
                : null;
    }
}
