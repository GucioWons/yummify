package com.guciowons.yummify.menu.application.dto.mapper;

import com.guciowons.yummify.common.i8n.application.dto.mapper.TranslatedStringMapper;
import com.guciowons.yummify.menu.application.dto.MenuClientDTO;
import com.guciowons.yummify.menu.application.dto.MenuManageDTO;
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
    MenuManageDTO mapToManageDTO(Menu entity);

    MenuClientDTO mapToClientDTO(Menu entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "active", ignore = true)
    Menu mapToSaveEntity(MenuManageDTO dto, @Context UUID restaurantId);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sections", ignore = true)
    @Mapping(target = "active", ignore = true)
    Menu mapToUpdateEntity(MenuManageDTO dto, @MappingTarget Menu entity);
}
