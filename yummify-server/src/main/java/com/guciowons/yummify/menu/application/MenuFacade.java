package com.guciowons.yummify.menu.application;

import com.guciowons.yummify.menu.application.dto.MenuClientDTO;
import com.guciowons.yummify.menu.application.dto.MenuManageDTO;
import com.guciowons.yummify.menu.application.dto.mapper.MenuMapper;
import com.guciowons.yummify.menu.application.usecase.*;
import com.guciowons.yummify.menu.domain.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MenuFacade {
    private final MenuCreateUsecase menuCreateUsecase;
    private final MenuUpdateUsecase menuUpdateUsecase;
    private final MenuGetActiveUsecase menuGetActiveUsecase;
    private final MenuActivateUsecase menuActivateUsecase;
    private final MenuDeactivateUsecase menuDeactivateUsecase;
    private final MenuMapper menuMapper;

    public MenuManageDTO create(MenuManageDTO dto, UUID restaurantId) {
        Menu menu = menuCreateUsecase.create(dto, restaurantId);
        return menuMapper.mapToManageDTO(menu);
    }

    public MenuManageDTO update(UUID id, MenuManageDTO dto, UUID restaurantId) {
        Menu menu = menuUpdateUsecase.update(id, dto, restaurantId);
        return menuMapper.mapToManageDTO(menu);
    }

    public MenuClientDTO getActive(UUID restaurantId) {
        Menu menu = menuGetActiveUsecase.get(restaurantId);
        return menuMapper.mapToClientDTO(menu);
    }

    public MenuManageDTO activate(UUID id, UUID restaurantId) {
        Menu menu = menuActivateUsecase.activate(id, restaurantId);
        return menuMapper.mapToManageDTO(menu);
    }

    public MenuManageDTO deactivate(UUID id, UUID restaurantId) {
        Menu menu = menuDeactivateUsecase.deactivate(id, restaurantId);
        return menuMapper.mapToManageDTO(menu);
    }
}
