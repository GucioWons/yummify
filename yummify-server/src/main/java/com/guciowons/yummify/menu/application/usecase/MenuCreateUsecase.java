package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.dish.DishExistencePort;
import com.guciowons.yummify.menu.domain.entity.update.MenuData;
import com.guciowons.yummify.menu.domain.entity.update.MenuEntryData;
import com.guciowons.yummify.menu.domain.exception.MenuEntryNotFoundException;
import com.guciowons.yummify.menu.domain.exception.MenuSectionNotFoundException;
import com.guciowons.yummify.menu.domain.port.in.MenuCreateUsecasePort;
import com.guciowons.yummify.menu.domain.port.out.MenuRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class MenuCreateUsecase implements MenuCreateUsecasePort {
    private final MenuRepositoryPort menuRepository;
    private final DishExistencePort dishExistencePort;

    @Override
    public Menu create(MenuData menuData) throws MenuSectionNotFoundException, MenuEntryNotFoundException {
        dishExistencePort.findMissing(getDishIds(menuData), menuData.restaurantId());
        return menuRepository.save(Menu.create(menuData));
    }

    private List<UUID> getDishIds(MenuData menuData) {
        return menuData.sections().stream()
                .flatMap(section -> section.entries().stream())
                .map(MenuEntryData::dishId)
                .toList();
    }
}
