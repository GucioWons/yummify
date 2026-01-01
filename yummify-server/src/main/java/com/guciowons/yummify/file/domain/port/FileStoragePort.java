package com.guciowons.yummify.file.domain.port;

import com.guciowons.yummify.file.application.FileContent;

public interface FileStoragePort {
    void upload(String storageKey, FileContent fileContent);
    void delete(String storageKey);
}
