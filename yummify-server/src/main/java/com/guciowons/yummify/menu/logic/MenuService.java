package com.guciowons.yummify.menu.logic;

import com.guciowons.yummify.common.core.service.TranslatableRestaurantScopedService;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.dish.PublicDishService;
import com.guciowons.yummify.menu.data.MenuRepository;
import com.guciowons.yummify.menu.dto.MenuClientDTO;
import com.guciowons.yummify.menu.dto.MenuListDTO;
import com.guciowons.yummify.menu.dto.MenuManageDTO;
import com.guciowons.yummify.menu.entity.Menu;
import com.guciowons.yummify.menu.exception.MenuNotFoundException;
import com.guciowons.yummify.menu.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class MenuService extends TranslatableRestaurantScopedService<Menu, MenuManageDTO, MenuClientDTO, MenuListDTO> {
    private final MenuSectionService menuSectionService;
    private final PublicDishService dishService;

    public MenuService(MenuRepository menuRepository, MenuMapper menuMapper, MenuSectionService menuSectionService, PublicDishService dishService) {
        super(menuRepository, menuMapper);
        this.menuSectionService = menuSectionService;
        this.dishService = dishService;
    }

    @Override
    protected void validate(MenuManageDTO dto) {
        dto.getSections().stream()
                .flatMap(section -> section.getEntries().stream())
                .forEach(entry -> dishService.validateDishId(entry.getDish().getId()));
    }

    @Override
    protected void afterMappingEntity(MenuManageDTO dto, Menu entity) {
        if (entity.getSections() == null) {
            entity.setSections(new ArrayList<>());
        } else {
            entity.getSections().clear();
        }

        dto.getSections().stream()
                .map(sectionDTO -> menuSectionService.upsertAndGet(sectionDTO, entity))
                .forEach(section -> entity.getSections().add(section));
    }

    @Override
    protected void afterMappingManageDTO(MenuManageDTO dto, Menu entity) {
        dto.getSections().stream()
                .flatMap(section -> section.getEntries().stream())
                .forEach(entry -> entry.setDish(dishService.getClientDTO(entry.getDish().getId())));
    }

    @Override
    protected SingleApiErrorException getNotFoundException(UUID id) {
        return new MenuNotFoundException(id);
    }
}
