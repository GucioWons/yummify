package com.guciowons.yummify.menu.application.section;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.menu.application.section.model.CreateMenuSectionCommand;
import com.guciowons.yummify.menu.application.section.model.UpdateMenuSectionNameCommand;
import com.guciowons.yummify.menu.application.section.model.UpdateMenuSectionPositionCommand;
import com.guciowons.yummify.menu.application.section.model.mapper.MenuSectionCommandMapper;
import com.guciowons.yummify.menu.application.section.port.MenuSectionFacadePort;
import com.guciowons.yummify.menu.application.section.usecase.CreateMenuSectionUsecase;
import com.guciowons.yummify.menu.application.section.usecase.UpdateMenuSectionNameUsecase;
import com.guciowons.yummify.menu.application.section.usecase.UpdateMenuSectionPositionUsecase;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class MenuSectionFacade implements MenuSectionFacadePort {
    private final CreateMenuSectionUsecase createMenuSectionUsecase;
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
