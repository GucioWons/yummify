package com.guciowons.yummify.common.file.domain.model;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;
import com.guciowons.yummify.common.core.domain.entity.ValueObject;
import com.guciowons.yummify.common.file.domain.exception.InvalidStorageKeyException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class File {
    private Id id;
    private StorageKey storageKey;

    public static File create(StorageKey storageKey) {
        return new File(Id.random(), storageKey);
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

        public static StorageKey of(String value) {
            return new StorageKey(value);
        }
    }
}
