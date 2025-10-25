package com.guciowons.yummify.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface PublicFileService {
    String create(String directory, MultipartFile file);

    String update(UUID id, String directory, MultipartFile file);

    void delete(UUID id);
}
