package com.guciowons.yummify.menu.mapper;

import com.guciowons.yummify.common.core.mapper.TranslatableMapper;
import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.menu.data.MenuEntryRepository;
import com.guciowons.yummify.menu.dto.entry.MenuEntryDTO;
import com.guciowons.yummify.menu.dto.section.MenuSectionClientDTO;
import com.guciowons.yummify.menu.dto.section.MenuSectionDTO;
import com.guciowons.yummify.menu.dto.section.MenuSectionManageDTO;
import com.guciowons.yummify.menu.entity.MenuEntry;
import com.guciowons.yummify.menu.entity.MenuSection;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {
                TranslatedStringMapper.class,
                MenuEntryMapper.class
        }
)
public abstract class MenuSectionMapper implements TranslatableMapper<MenuSection, MenuSectionDTO, MenuSectionManageDTO, MenuSectionClientDTO> {
    @Autowired
    private MenuEntryRepository menuEntryRepository;

    @Autowired
    private MenuEntryMapper menuEntryMapper;

    public abstract MenuSectionManageDTO mapToManageDTO(MenuSection entity);

    public abstract MenuSectionClientDTO mapToClientDTO(MenuSection entity);

    @Mapping(target = "id", ignore = true)
    public abstract MenuSection mapToSaveEntity(MenuSectionManageDTO dto);

    @Mapping(target = "id", ignore = true)
    public abstract MenuSection mapToUpdateEntity(MenuSectionManageDTO dto, @MappingTarget MenuSection entity);

    @AfterMapping
    protected void updateEntries(MenuSectionManageDTO dto, @MappingTarget MenuSection entity) {
        if (entity.getEntries() == null) {
            entity.setEntries(new ArrayList<>());
        } else {
            entity.getEntries().clear();
        }

        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();

        for (MenuEntryDTO entryDTO : dto.getEntries()) {
            MenuEntry toAdd;
            if (entryDTO.getId() == null) {
                toAdd = menuEntryMapper.mapToSaveEntity(entryDTO);
            } else {
                toAdd = menuEntryRepository.findByIdAndSectionMenuRestaurantId(entryDTO.getId(), restaurantId)
                        .orElseThrow();
            }
            entity.getEntries().add(toAdd);
        }
    }
}
