package com.guciowons.yummify.menu.logic;

import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.menu.data.MenuRepository;
import com.guciowons.yummify.menu.dto.MenuClientDTO;
import com.guciowons.yummify.menu.dto.MenuManageDTO;
import com.guciowons.yummify.menu.entity.Menu;
import com.guciowons.yummify.menu.exception.NoActiveMenuException;
import com.guciowons.yummify.menu.mapper.MenuMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuActiveService {
    private final MenuService menuService;
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    public MenuClientDTO getActive() {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        Menu menu = menuRepository.findByRestaurantIdAndActive(restaurantId, true)
                .orElseThrow(NoActiveMenuException::new);
        return menuMapper.mapToClientDTO(menu);
    }

    @Transactional
    public MenuManageDTO activate(UUID id) {
        Menu menu = menuService.getEntityById(id);
        menuRepository.findByRestaurantIdAndActive(menu.getRestaurantId(), true)
                .ifPresent(activeMenu -> activeMenu.setActive(false));
        menu.setActive(true);
        return menuMapper.mapToManageDTO(menuRepository.save(menu));
    }

    @Transactional
    public MenuManageDTO deactivate(UUID id) {
        Menu menu = menuService.getEntityById(id);
        menu.setActive(false);
        return menuMapper.mapToManageDTO(menuRepository.save(menu));
    }
}
