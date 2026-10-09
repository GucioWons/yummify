package com.guciowons.yummify.common.file.application;

import com.guciowons.yummify.common.file.domain.model.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileStoragePort {
    void store(File.StorageKey storageKey, MultipartFile file);
    void remove(File.StorageKey storageKey);
}
