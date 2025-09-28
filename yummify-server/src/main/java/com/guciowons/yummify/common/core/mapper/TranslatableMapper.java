package com.guciowons.yummify.common.core.mapper;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.core.entity.BaseEntity;

public interface TranslatableMapper<Entity extends BaseEntity, DTO extends BaseEntityDTO, ManageDTO extends DTO, ClientDTO extends DTO> {
    ManageDTO mapToManageDTO(Entity entity);
    ClientDTO mapToClientDTO(Entity entity);
    Entity mapToSaveEntity(ManageDTO dto);
    Entity mapToUpdateEntity(ManageDTO dto, Entity entity);
}
