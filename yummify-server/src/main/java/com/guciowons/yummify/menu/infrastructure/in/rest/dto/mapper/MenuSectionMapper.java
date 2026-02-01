package com.guciowons.yummify.menu.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.menu.domain.entity.update.MenuSectionData;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.MenuSectionClientDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.MenuSectionManageDto;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                TranslatedStringMapper.class,
                MenuEntryMapper.class
        }
)
public interface MenuSectionMapper {
    MenuSectionManageDto toManageDto(MenuSection entity);
    MenuSectionClientDto toClientDto(MenuSection entity);

    MenuSectionData toData(MenuSectionManageDto dto);
}
