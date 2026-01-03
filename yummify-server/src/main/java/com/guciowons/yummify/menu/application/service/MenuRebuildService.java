package com.guciowons.yummify.menu.application.service;

import com.guciowons.yummify.menu.application.dto.MenuManageDTO;
import com.guciowons.yummify.menu.application.dto.MenuSectionManageDTO;
import com.guciowons.yummify.menu.application.dto.mapper.MenuEntryMapper;
import com.guciowons.yummify.menu.application.dto.mapper.MenuSectionMapper;
import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MenuRebuildService {
    private final MenuSectionMapper menuSectionMapper;
    private final MenuEntryMapper menuEntryMapper;

    public void rebuild(Menu menu, MenuManageDTO dto) {
        List<MenuSection> newSections = dto.sections().stream()
                .map(this::buildSection)
                .toList();

        menu.rebuildSections(newSections);
    }

    private MenuSection buildSection(MenuSectionManageDTO dto) {
        MenuSection section = menuSectionMapper.mapToSaveEntity(dto);

        List<MenuEntry> newEntries = dto.entries().stream()
                .map(menuEntryMapper::mapToSaveEntity)
                .toList();

        section.rebuildEntries(newEntries);
        return section;
    }
}
