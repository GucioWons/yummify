package com.guciowons.yummify.common.file.application;

import com.guciowons.yummify.common.file.domain.model.File;

import java.net.URL;

public interface FileUrlProviderPort {
    URL getUrl(File.StorageKey storageKey);
}
