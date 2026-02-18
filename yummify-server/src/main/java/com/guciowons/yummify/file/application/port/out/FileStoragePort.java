package com.guciowons.yummify.file.application.port.out;

import com.guciowons.yummify.file.application.model.FileContent;
import com.guciowons.yummify.file.domain.entity.File;

public interface FileStoragePort {
    void store(File.StorageKey storageKey, FileContent fileContent);
    void remove(File.StorageKey storageKey);
}
