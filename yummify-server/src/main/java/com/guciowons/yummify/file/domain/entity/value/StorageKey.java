package com.guciowons.yummify.file.domain.entity.value;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;
import com.guciowons.yummify.restaurant.RestaurantId;

import java.util.UUID;

public record StorageKey(String value) implements ValueObject<String> {
    public StorageKey {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("StorageKey cannot be blank");
        }
        if (value.startsWith("/")) {
            throw new IllegalArgumentException("StorageKey cannot start with /");
        }
        if (value.contains("..")) {
            throw new IllegalArgumentException("StorageKey cannot contain '..'");
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
