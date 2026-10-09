package com.guciowons.yummify.dish.infrastructure.out.jpa.entity.mapper;

import com.guciowons.yummify.common.file.domain.model.File;
import com.guciowons.yummify.dish.infrastructure.out.jpa.entity.JpaDishImage;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface JpaDishImageMapper {
    File toDomain(JpaDishImage jpaDishImage);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "storageKey", source = "storageKey.value")
    JpaDishImage toJpa(File image);

    default File.Id toId(UUID id) {
        return File.Id.of(id);
    }

    default File.StorageKey toStorageKey(String storageKey) {
        return File.StorageKey.of(storageKey);
    }
}
