package com.guciowons.yummify.file;

import com.guciowons.yummify.restaurant.RestaurantId;
import org.springframework.modulith.NamedInterface;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.UUID;

@NamedInterface("FileFacadePort")
public interface FileFacadePort {
    UUID create(String directory, MultipartFile file, RestaurantId restaurantId);

    void update(UUID id, String directory, MultipartFile file, RestaurantId restaurantId);

    void delete(UUID id, RestaurantId restaurantId);

    URL getUrl(UUID id, RestaurantId restaurantId);
}
