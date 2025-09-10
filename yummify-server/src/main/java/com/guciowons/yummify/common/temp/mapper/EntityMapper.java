package com.guciowons.yummify.common.temp.mapper;

public interface EntityMapper<DTO, Entity> {
    DTO mapToDTO(Entity entity);
    Entity mapToSaveEntity(DTO dto);
    Entity mapToUpdateEntity(DTO dto, Entity entity);
}
