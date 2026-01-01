package com.guciowons.yummify.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface PublicFileFacade {
    UUID create(String directory, MultipartFile file);

    void update(UUID id, String directory, MultipartFile file);

    void delete(UUID id);

    String getPresignedUrl(UUID id);
}
