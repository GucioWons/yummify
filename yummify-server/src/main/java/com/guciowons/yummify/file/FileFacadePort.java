package com.guciowons.yummify.file;

import org.springframework.modulith.NamedInterface;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.UUID;

@NamedInterface("FileFacadePort")
public interface FileFacadePort {
    UUID create(String directory, MultipartFile file, UUID restaurantId);

    void update(UUID id, String directory, MultipartFile file, UUID restaurantId);

    void delete(UUID id, UUID restaurantId);

    URL getUrl(UUID id, UUID restaurantId);
}
