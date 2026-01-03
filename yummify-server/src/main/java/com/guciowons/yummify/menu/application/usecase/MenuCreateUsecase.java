package com.guciowons.yummify.menu.application.usecase;

import com.guciowons.yummify.menu.application.dto.MenuEntryDTO;
import com.guciowons.yummify.menu.application.dto.MenuManageDTO;
import com.guciowons.yummify.menu.application.dto.mapper.MenuMapper;
import com.guciowons.yummify.menu.application.service.MenuRebuildService;
import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.dish.exposed.DishExistencePort;
import com.guciowons.yummify.menu.domain.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MenuCreateUsecase {
    private final MenuRepository menuRepository;
    private final MenuRebuildService menuRebuildService;
    private final DishExistencePort dishExistencePort;
    private final MenuMapper menuMapper;

    public Menu create(MenuManageDTO dto, UUID restaurantId) {
        dishExistencePort.assertAllExist(getDishIds(dto), restaurantId);

        Menu menu = menuMapper.mapToSaveEntity(dto, restaurantId);

        menuRebuildService.rebuild(menu, dto);

        return menuRepository.save(menu);
    }

    private List<UUID> getDishIds(MenuManageDTO dto) {
        return dto.sections().stream()
                .flatMap(section -> section.entries().stream())
                .map(MenuEntryDTO::dishId)
                .toList();
    }
}
