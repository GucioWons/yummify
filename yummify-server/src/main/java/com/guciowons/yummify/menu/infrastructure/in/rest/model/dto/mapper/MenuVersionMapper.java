package com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.mapper;

import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.MenuVersionArchivedListDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.MenuVersionClientDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.model.dto.MenuVersionManageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = MenuSectionMapper.class)
public interface MenuVersionMapper {
    MenuVersionManageDto toManageDto(MenuVersion menuVersion);
    MenuVersionClientDto toClientDto(MenuVersion menuVersion);

    @Mapping(target = "id", source = "id.value")
    MenuVersionArchivedListDto toArchivedListDto(MenuVersion menuVersion);
}
