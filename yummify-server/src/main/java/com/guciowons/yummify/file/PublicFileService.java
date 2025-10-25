package com.guciowons.yummify.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface PublicFileService {
    void create(String directory, MultipartFile file);

    void update(UUID id, String directory, MultipartFile file);

    void delete(UUID id);

    String getPresignedUrl(UUID id);
}
