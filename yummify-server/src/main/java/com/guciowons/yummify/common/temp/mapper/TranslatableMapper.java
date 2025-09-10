package com.guciowons.yummify.common.temp.mapper;

public interface TranslatableMapper<Entity, DTO, ManageDTO extends DTO, ClientDTO extends DTO> {
    ManageDTO mapToManageDTO(Entity entity);
    ClientDTO mapToClientDTO(Entity entity);
    Entity mapToSaveEntity(ManageDTO dto);
    Entity mapToUpdateEntity(ManageDTO dto, Entity entity);
}
