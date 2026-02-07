package com.guciowons.yummify.menu.application;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.common.exception.application.handler.DomainExceptionHandler;
import com.guciowons.yummify.menu.application.model.CreateMenuSectionCommand;
import com.guciowons.yummify.menu.application.model.UpdateMenuSectionCommand;
import com.guciowons.yummify.menu.application.model.mapper.MenuSectionCommandMapper;
import com.guciowons.yummify.menu.application.usecase.CreateMenuSectionUsecase;
import com.guciowons.yummify.menu.application.usecase.UpdateMenuSectionUsecase;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class MenuSectionFacade {
    private final CreateMenuSectionUsecase createMenuSectionUsecase;
    private final UpdateMenuSectionUsecase updateMenuSectionUsecase;
    private final MenuSectionCommandMapper menuSectionCommandMapper;
    private final DomainExceptionHandler menuDomainExceptionHandler;

    public MenuSection create(UUID restaurantId, Map<String, String> name, Integer position) {
        CreateMenuSectionCommand command = menuSectionCommandMapper.toCreateMenuSectionCommand(
                restaurantId,
                name,
                position
        );

        return menuDomainExceptionHandler.handle(() -> createMenuSectionUsecase.create(command));
    }

    public MenuSection update(
            UUID id,
            UUID restaurantId,
            Map<String, String> name,
            Integer position,
            List<MenuEntrySnapshot> menuEntrySnapshots
    ) {
        UpdateMenuSectionCommand command = menuSectionCommandMapper.toUpdateMenuSectionCommand(
                id,
                restaurantId,
                name,
                position,
                menuEntrySnapshots
        );

        return menuDomainExceptionHandler.handle(() -> updateMenuSectionUsecase.update(command));
    }
}
