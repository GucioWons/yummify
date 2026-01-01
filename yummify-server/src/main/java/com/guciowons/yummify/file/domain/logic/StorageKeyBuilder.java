package com.guciowons.yummify.file.domain.logic;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StorageKeyBuilder {
    public String buildStorageKey(String fileName, UUID restaurantId, String directory) {
        String uniqueName = UUID.randomUUID() + "-" + fileName;
        return String.join("/", restaurantId.toString(), directory, uniqueName);
    }
}
