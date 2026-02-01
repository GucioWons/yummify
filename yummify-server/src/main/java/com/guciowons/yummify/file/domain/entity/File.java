package com.guciowons.yummify.file.domain.entity;

import com.guciowons.yummify.file.domain.entity.value.FileId;
import com.guciowons.yummify.file.domain.entity.value.StorageKey;
import com.guciowons.yummify.restaurant.RestaurantId;
import jakarta.persistence.*;
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
    @AttributeOverride(name = "value", column = @Column(name = "id", nullable = false))
    private FileId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "restaurant_id", nullable = false))
    private RestaurantId restaurantId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "storage_key", nullable = false))
    private StorageKey storageKey;

    public static File of(RestaurantId restaurantId, StorageKey storageKey) {
        return new File(FileId.random(), restaurantId, storageKey);
    }

    public void changeStorageKey(StorageKey newKey) {
        this.storageKey = newKey;
    }
}
