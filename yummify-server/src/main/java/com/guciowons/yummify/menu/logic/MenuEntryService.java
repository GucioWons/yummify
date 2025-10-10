package com.guciowons.yummify.menu.logic;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.menu.data.MenuEntryRepository;
import com.guciowons.yummify.menu.dto.entry.MenuEntryDTO;
import com.guciowons.yummify.menu.entity.MenuEntry;
import com.guciowons.yummify.menu.entity.MenuSection;
import com.guciowons.yummify.menu.mapper.MenuEntryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuEntryService {
    private final MenuEntryRepository menuEntryRepository;
    private final MenuEntryMapper menuEntryMapper;

    public MenuEntry upsertAndGet(MenuEntryDTO dto, MenuSection section) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();

        MenuEntry entity;
        if (dto.getId() == null) {
            entity = menuEntryMapper.mapToSaveEntity(dto);
        } else {
            MenuEntry toUpdate = menuEntryRepository.findByIdAndSectionMenuRestaurantId(dto.getId(), restaurantId)
                    .orElseThrow();
            entity = menuEntryMapper.mapToUpdateEntity(dto, toUpdate);
        }
        entity.setSection(section);
        return entity;
    }
}
