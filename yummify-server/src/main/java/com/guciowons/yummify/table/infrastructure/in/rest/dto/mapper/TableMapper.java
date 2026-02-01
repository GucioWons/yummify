package com.guciowons.yummify.table.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.infrastructure.in.rest.dto.TableDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TableMapper {
    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "name", source = "name.value")
    TableDTO mapToDTO(Table entity);
}

