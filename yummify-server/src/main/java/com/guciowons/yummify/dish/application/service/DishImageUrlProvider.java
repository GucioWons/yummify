package com.guciowons.yummify.dish.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.common.file.domain.model.File;
import com.guciowons.yummify.common.file.application.FileUrlProviderPort;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class DishImageUrlProvider {
    private final FileUrlProviderPort fileUrlProvider;

    public String get(File file) {
        return file != null
                ? fileUrlProvider.getUrl(file.getStorageKey()).toString()
                : null;
    }
}
