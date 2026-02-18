package com.guciowons.yummify.file.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.core.domain.entity.ValueObject;
import com.guciowons.yummify.file.domain.exception.InvalidStorageKeyException;
import com.guciowons.yummify.file.domain.model.Directory;
import com.guciowons.yummify.file.domain.model.Filename;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class File {
    private Id id;
    private RestaurantId restaurantId;
    private StorageKey storageKey;

    public static File create(RestaurantId restaurantId, StorageKey storageKey) {
        return new File(Id.random(), restaurantId, storageKey);
    }

    public void changeStorageKey(StorageKey storageKey) {
        this.storageKey = storageKey;
    }

    public record Id(UUID value) implements IdValueObject {
        public static Id random() {
            return new Id(UUID.randomUUID());
        }

        public static Id of(UUID value) {
            return new Id(value);
        }
    }

    public record RestaurantId(UUID value) implements IdValueObject {
        public static RestaurantId of(UUID value) {
            return new RestaurantId(value);
        }
    }

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
}
