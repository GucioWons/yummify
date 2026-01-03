package com.guciowons.yummify.menu.domain.service;

import com.guciowons.yummify.menu.domain.entity.Menu;
import com.guciowons.yummify.menu.domain.exception.MenuIsActiveException;
import com.guciowons.yummify.menu.domain.exception.MenuIsNotActiveException;
import com.guciowons.yummify.menu.domain.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuActivationService {
    private final MenuRepository menuRepository;

    public void activate(Menu menu) {
        if (menu.isActive()) {
            throw new MenuIsActiveException();
        }

        menuRepository.findByRestaurantIdAndActive(menu.getRestaurantId(), true)
                .ifPresent(activeMenu -> activeMenu.setActive(false));

        menu.setActive(true);
    }

    public void deactivate(Menu menu) {
        if (!menu.isActive()) {
            throw new MenuIsNotActiveException();
        }
        menu.setActive(false);
    }
}
