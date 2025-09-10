package com.guciowons.yummify.table.mapper;

import com.guciowons.yummify.common.core.mapper.BaseEntityMapper;
import com.guciowons.yummify.table.TableDTO;
import com.guciowons.yummify.table.entity.Table;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TableMapper extends BaseEntityMapper<TableDTO, Table> {
    TableDTO mapToDTO(Table entity);

    @Mapping(target = "id", ignore = true)
    Table mapToEntity(TableDTO dto);

    @Mapping(target = "id", ignore = true)
    Table mapToUpdateEntity(TableDTO dto, @MappingTarget Table table);
}

