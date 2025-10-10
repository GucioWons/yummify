package com.guciowons.yummify.common.core.mapper;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.core.entity.BaseEntity;

public interface TranslatableMapper<
        Entity extends BaseEntity,
        ManageDTO extends BaseEntityDTO,
        ClientDTO extends BaseEntityDTO,
        ListDTO extends BaseEntityDTO>
{
    ManageDTO mapToManageDTO(Entity entity);
    ClientDTO mapToClientDTO(Entity entity);
    ListDTO mapToListDTO(Entity entity);
    Entity mapToSaveEntity(ManageDTO dto);
    Entity mapToUpdateEntity(ManageDTO dto, Entity entity);
}
