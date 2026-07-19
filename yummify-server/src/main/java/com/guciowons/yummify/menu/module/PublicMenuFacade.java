package com.guciowons.yummify.menu.module;

import com.guciowons.yummify.menu.PublicMenuFacadePort;
import com.guciowons.yummify.menu.application.entry.port.MenuEntryFacadePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PublicMenuFacade implements PublicMenuFacadePort {
    private final MenuEntryFacadePort menuEntryFacadePort;

    @Override
    public BigDecimal getPriceByDishId(UUID restaurantId, UUID dishId) {
        return menuEntryFacadePort.getByDishId(restaurantId, dishId).getPrice().value();
    }
}
