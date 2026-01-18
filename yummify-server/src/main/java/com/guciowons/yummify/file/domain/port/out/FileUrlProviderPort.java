package com.guciowons.yummify.file.domain.port.out;

import com.guciowons.yummify.file.domain.entity.value.FileUrl;
import com.guciowons.yummify.file.domain.entity.value.StorageKey;

public interface FileUrlProviderPort {
    FileUrl getUrl(StorageKey storageKey);
}
