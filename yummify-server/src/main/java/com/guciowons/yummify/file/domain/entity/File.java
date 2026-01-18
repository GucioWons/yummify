package com.guciowons.yummify.file.domain.entity;

import com.guciowons.yummify.file.domain.entity.value.FileId;
import com.guciowons.yummify.file.domain.entity.value.StorageKey;
import com.guciowons.yummify.restaurant.RestaurantId;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "file", schema = "file")
public class File {
    @EmbeddedId
    private FileId id;

    @Embedded
    private RestaurantId restaurantId;

    @Embedded
    private StorageKey storageKey;

    public static File of(RestaurantId restaurantId, StorageKey storageKey) {
        return new File(FileId.random(), restaurantId, storageKey);
    }

    public void changeStorageKey(StorageKey newKey) {
        this.storageKey = newKey;
    }
}
