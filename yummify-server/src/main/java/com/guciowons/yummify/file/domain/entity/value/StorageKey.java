package com.guciowons.yummify.file.domain.entity.value;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;
import com.guciowons.yummify.file.domain.exception.InvalidStorageKeyException;
import com.guciowons.yummify.restaurant.RestaurantId;

import java.util.UUID;

public record StorageKey(String value) implements ValueObject<String> {
    public StorageKey {
        if (value == null || value.isBlank()) {
            throw InvalidStorageKeyException.blank();
        }
        if (value.startsWith("/")) {
            throw InvalidStorageKeyException.startsWithSlash();
        }
        if (value.contains("..")) {
            throw InvalidStorageKeyException.containsDots();
        }
    }

    public static StorageKey of(Filename filename, RestaurantId restaurantId, Directory directory) {
        String storageKey = "%s/%s/%s-%s".formatted(
                restaurantId.value(),
                directory.value(),
                UUID.randomUUID(),
                filename.value()
        );

        return new StorageKey(storageKey);
    }
}
