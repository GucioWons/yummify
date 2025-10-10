package com.guciowons.yummify.menu.logic;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.menu.data.MenuSectionRepository;
import com.guciowons.yummify.menu.dto.section.MenuSectionManageDTO;
import com.guciowons.yummify.menu.entity.Menu;
import com.guciowons.yummify.menu.entity.MenuSection;
import com.guciowons.yummify.menu.mapper.MenuSectionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuSectionService {
    private final MenuEntryService menuEntryService;
    private final MenuSectionRepository menuSectionRepository;
    private final MenuSectionMapper menuSectionMapper;

    public MenuSection upsertAndGet(MenuSectionManageDTO dto, Menu menu) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();

        MenuSection entity;
        if (dto.getId() == null) {
            entity = menuSectionMapper.mapToSaveEntity(dto);
        } else {
            MenuSection toUpdate = menuSectionRepository.findByIdAndMenuRestaurantId(dto.getId(), restaurantId)
                    .orElseThrow();
            entity = menuSectionMapper.mapToUpdateEntity(dto, toUpdate);
        }
        entity.setMenu(menu);
        updateMenuEntries(entity, dto);
        return entity;
    }

    private void updateMenuEntries(MenuSection entity, MenuSectionManageDTO dto) {
        if (entity.getEntries() == null) {
            entity.setEntries(new ArrayList<>());
        } else {
            entity.getEntries().clear();
        }

        dto.getEntries().stream()
                .map(entryDTO -> menuEntryService.upsertAndGet(entryDTO, entity))
                .forEach(entry -> entity.getEntries().add(entry));
    }
}
