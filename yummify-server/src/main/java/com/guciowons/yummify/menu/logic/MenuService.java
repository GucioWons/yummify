package com.guciowons.yummify.menu.logic;

import com.guciowons.yummify.common.core.service.TranslatableRestaurantScopedService;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.menu.data.MenuRepository;
import com.guciowons.yummify.menu.dto.MenuClientDTO;
import com.guciowons.yummify.menu.dto.MenuDTO;
import com.guciowons.yummify.menu.dto.MenuManageDTO;
import com.guciowons.yummify.menu.entity.Menu;
import com.guciowons.yummify.menu.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MenuService extends TranslatableRestaurantScopedService<Menu, MenuDTO, MenuManageDTO, MenuClientDTO, MenuRepository, MenuMapper> {
    public MenuService(MenuRepository menuRepository, MenuMapper menuMapper) {
        super(menuRepository, menuMapper);
    }

    @Override
    protected SingleApiErrorException getNotFoundException(UUID id) {
        return null;
    }
}
