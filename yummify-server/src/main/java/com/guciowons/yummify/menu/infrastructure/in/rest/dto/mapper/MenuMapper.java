package com.guciowons.yummify.menu.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.menu.domain.entity.update.MenuData;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.MenuClientDto;
import com.guciowons.yummify.menu.infrastructure.in.rest.dto.MenuManageDto;
import com.guciowons.yummify.menu.domain.entity.Menu;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                TranslatedStringMapper.class,
                MenuSectionMapper.class
        }
)
public interface MenuMapper {
    @Mapping(target = "id", source = "id.value")
    MenuManageDto toManageDto(Menu menu);

    @Mapping(target = "id", source = "id.value")
    MenuClientDto toClientDto(Menu menu);

    @Mapping(target = "restaurantId", expression = "java(restaurantId)")
    MenuData toData(MenuManageDto dto, UUID restaurantId);
}
