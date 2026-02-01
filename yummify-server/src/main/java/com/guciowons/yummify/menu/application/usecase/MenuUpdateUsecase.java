package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.dish.DishExistencePort;
import com.guciowons.yummify.menu.application.service.MenuLookupService;
import com.guciowons.yummify.menu.domain.entity.update.MenuData;
import com.guciowons.yummify.menu.domain.entity.update.MenuEntryData;
import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.entity.value.MenuId;
import com.guciowons.yummify.menu.domain.exception.MenuEntryNotFoundException;
import com.guciowons.yummify.menu.domain.exception.MenuNotFoundException;
import com.guciowons.yummify.menu.domain.exception.MenuSectionNotFoundException;
import com.guciowons.yummify.menu.domain.port.in.MenuUpdateUsecasePort;
import com.guciowons.yummify.menu.domain.port.out.MenuRepositoryPort;
import com.guciowons.yummify.restaurant.RestaurantId;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class MenuUpdateUsecase implements MenuUpdateUsecasePort {
    private final MenuLookupService menuLookupService;
    private final MenuRepositoryPort menuRepository;
    private final DishExistencePort dishExistencePort;

    @Override
    public Menu update(UUID id, MenuData menuData) throws MenuNotFoundException, MenuSectionNotFoundException, MenuEntryNotFoundException {
        dishExistencePort.findMissing(getDishIds(menuData), menuData.restaurantId());

        Menu menu = menuLookupService.getByIdAndRestaurantId(MenuId.of(id), RestaurantId.of(menuData.restaurantId()));
        menu.syncSections(menuData.sections());
        return menuRepository.save(menu);
    }

    private List<UUID> getDishIds(MenuData menuData) {
        return menuData.sections().stream()
                .flatMap(section -> section.entries().stream())
                .map(MenuEntryData::dishId)
                .toList();
    }
}
