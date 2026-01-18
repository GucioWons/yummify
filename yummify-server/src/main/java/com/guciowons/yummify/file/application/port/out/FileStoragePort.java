package com.guciowons.yummify.file.application.port.out;

import com.guciowons.yummify.file.application.model.FileContent;
import com.guciowons.yummify.file.domain.entity.value.StorageKey;

public interface FileStoragePort {
    void store(StorageKey storageKey, FileContent fileContent);
    void remove(StorageKey storageKey);
}
