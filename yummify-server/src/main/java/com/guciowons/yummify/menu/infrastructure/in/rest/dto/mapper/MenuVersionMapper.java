package com.guciowons.yummify.menu.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.MenuVersionClientDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.MenuVersionManageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = MenuSectionMapper.class)
public interface MenuVersionMapper {
    MenuVersionManageDto toManageDto(MenuVersion menuVersion);
    MenuVersionClientDto toClientDto(MenuVersion menuVersion);
}
