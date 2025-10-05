package com.guciowons.yummify.menu.mapper;

import com.guciowons.yummify.common.core.mapper.TranslatableMapper;
import com.guciowons.yummify.common.i8n.TranslatedStringMapper;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.menu.data.MenuSectionRepository;
import com.guciowons.yummify.menu.dto.MenuClientDTO;
import com.guciowons.yummify.menu.dto.MenuDTO;
import com.guciowons.yummify.menu.dto.MenuManageDTO;
import com.guciowons.yummify.menu.dto.section.MenuSectionManageDTO;
import com.guciowons.yummify.menu.entity.Menu;
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
                MenuSectionMapper.class
        }
)
public abstract class MenuMapper implements TranslatableMapper<Menu, MenuDTO, MenuManageDTO, MenuClientDTO> {
    @Autowired
    private MenuSectionMapper menuSectionMapper;

    @Autowired
    private MenuSectionRepository menuSectionRepository;

    public abstract MenuManageDTO mapToManageDTO(Menu entity);

    public abstract MenuClientDTO mapToClientDTO(Menu entity);

    @Mapping(target = "id", ignore = true)
    public abstract Menu mapToSaveEntity(MenuManageDTO dto);

    @Mapping(target = "id", ignore = true)
    public abstract Menu mapToUpdateEntity(MenuManageDTO dto, @MappingTarget Menu entity);

    @AfterMapping
    protected void updateEntries(MenuManageDTO dto, @MappingTarget Menu entity) {
        if (entity.getSections() == null) {
            entity.setSections(new ArrayList<>());
        } else {
            entity.getSections().clear();
        }

        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();

        for (MenuSectionManageDTO entryDTO : dto.getSections()) {
            MenuSection toAdd;
            if (entryDTO.getId() == null) {
                toAdd = menuSectionMapper.mapToSaveEntity(entryDTO);
            } else {
                toAdd = menuSectionRepository.findByIdAndMenuRestaurantId(entryDTO.getId(), restaurantId)
                        .orElseThrow();
            }
            entity.getSections().add(toAdd);
        }
    }
}
