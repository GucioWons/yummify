package com.guciowons.yummify.common.core.mapper;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.core.entity.BaseEntity;

public interface BaseEntityMapper<DTO extends BaseEntityDTO, Entity extends BaseEntity> {
    DTO mapToDTO(Entity entity);
    Entity mapToSaveEntity(DTO dto);
    Entity mapToUpdateEntity(DTO dto, Entity entity);
}
