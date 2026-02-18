package com.guciowons.yummify.file.application.port.out;

import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.domain.model.FileUrl;

public interface FileUrlProviderPort {
    FileUrl getUrl(File.StorageKey storageKey);
}
