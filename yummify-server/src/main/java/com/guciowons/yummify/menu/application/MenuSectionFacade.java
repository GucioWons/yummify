package com.guciowons.yummify.menu.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.menu.application.model.CreateMenuSectionCommand;
import com.guciowons.yummify.menu.application.model.UpdateMenuSectionEntriesCommand;
import com.guciowons.yummify.menu.application.model.UpdateMenuSectionNameCommand;
import com.guciowons.yummify.menu.application.model.UpdateMenuSectionPositionCommand;
import com.guciowons.yummify.menu.application.model.mapper.MenuSectionCommandMapper;
import com.guciowons.yummify.menu.application.port.MenuSectionFacadePort;
import com.guciowons.yummify.menu.application.usecase.CreateMenuSectionUsecase;
import com.guciowons.yummify.menu.application.usecase.UpdateMenuSectionEntriesUsecase;
import com.guciowons.yummify.menu.application.usecase.UpdateMenuSectionNameUsecase;
import com.guciowons.yummify.menu.application.usecase.UpdateMenuSectionPositionUsecase;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class MenuSectionFacade implements MenuSectionFacadePort {
    private final CreateMenuSectionUsecase createMenuSectionUsecase;
    private final UpdateMenuSectionEntriesUsecase updateMenuSectionEntriesUsecase;
    private final UpdateMenuSectionNameUsecase updateMenuSectionNameUsecase;
    private final UpdateMenuSectionPositionUsecase updateMenuSectionPositionUsecase;
    private final MenuSectionCommandMapper menuSectionCommandMapper;

    @Override
    public MenuSection create(UUID restaurantId, Map<String, String> name) {
        CreateMenuSectionCommand command = menuSectionCommandMapper.toCreateMenuSectionCommand(
                restaurantId,
                name
        );

        return createMenuSectionUsecase.create(command);
    }

    @Override
    public MenuSection updateEntries(UUID id, UUID restaurantId, List<MenuEntrySnapshot> entrySnapshots) {
        UpdateMenuSectionEntriesCommand command = menuSectionCommandMapper.toUpdateMenuSectionEntriesCommand(
                id,
                restaurantId,
                entrySnapshots
        );

        return updateMenuSectionEntriesUsecase.update(command);
    }

    @Override
    public MenuSection updateName(UUID id, UUID restaurantId, Map<String, String> name) {
        UpdateMenuSectionNameCommand command = menuSectionCommandMapper.toUpdateMenuSectionNameCommand(
                id,
                restaurantId,
                name
        );

        return updateMenuSectionNameUsecase.update(command);
    }

    @Override
    public List<MenuSection> updatePosition(UUID id, UUID restaurantId, Integer position) {
        UpdateMenuSectionPositionCommand command = menuSectionCommandMapper.toUpdateMenuSectionPositionCommand(
                id,
                restaurantId,
                position
        );

        return updateMenuSectionPositionUsecase.update(command);
    }
}
