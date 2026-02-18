package com.guciowons.yummify.file.infrastructure.out.jpa.entity.mapper;

import com.guciowons.yummify.file.domain.entity.File;
import com.guciowons.yummify.file.infrastructure.out.jpa.entity.JpaFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface JpaFileMapper {
    File toDomain(JpaFile jpaFile);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "restaurantId", source = "restaurantId.value")
    @Mapping(target = "storageKey", source = "storageKey.value")
    JpaFile toJpa(File file);

    default File.Id toId(UUID id) {
        return File.Id.of(id);
    }

    default File.RestaurantId toRestaurantId(UUID id) {
        return File.RestaurantId.of(id);
    }

    default File.StorageKey toStorageKey(String storageKey) {
        return new File.StorageKey(storageKey);
    }
}
