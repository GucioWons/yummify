package com.guciowons.yummify.table.application.dto.mapper;

import com.guciowons.yummify.table.application.dto.TableDTO;
import com.guciowons.yummify.table.domain.entity.Table;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TableMapper {
    TableDTO mapToDTO(Table entity);

    @Mapping(target = "id", ignore = true)
    Table mapToEntity(TableDTO dto, @Context UUID restaurantId);

    @Mapping(target = "id", ignore = true)
    Table mapToUpdateEntity(TableDTO dto, @MappingTarget Table table);
}

