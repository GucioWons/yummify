package com.guciowons.yummify.menu.application.entry;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.menu.application.entry.model.CreateMenuEntryCommand;
import com.guciowons.yummify.menu.application.entry.model.UpdateMenuEntryCommand;
import com.guciowons.yummify.menu.application.entry.model.mapper.MenuEntryCommandMapper;
import com.guciowons.yummify.menu.application.entry.port.MenuEntryFacadePort;
import com.guciowons.yummify.menu.application.entry.usecase.CreateMenuEntryUsecase;
import com.guciowons.yummify.menu.application.entry.usecase.UpdateMenuEntryUsecase;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class MenuEntryFacade implements MenuEntryFacadePort {
    private final CreateMenuEntryUsecase createMenuEntryUsecase;
    private final UpdateMenuEntryUsecase updateMenuEntryUsecase;
    private final MenuEntryCommandMapper menuEntryCommandMapper;

    @Override
    public MenuEntry createEntry(UUID sectionId, UUID restaurantId, UUID dishId, BigDecimal price) {
        CreateMenuEntryCommand command = menuEntryCommandMapper.toCreateMenuEntryCommand(
                sectionId,
                restaurantId,
                dishId,
                price
        );

        return createMenuEntryUsecase.create(command);
    }

    @Override
    public MenuEntry updateEntry(UUID sectionId, UUID id, UUID restaurantId, UUID dishId, BigDecimal price) {
        UpdateMenuEntryCommand command = menuEntryCommandMapper.toUpdateMenuEntryCommand(
                sectionId,
                id,
                restaurantId,
                dishId,
                price
        );
        return updateMenuEntryUsecase.update(command);
    }
}
