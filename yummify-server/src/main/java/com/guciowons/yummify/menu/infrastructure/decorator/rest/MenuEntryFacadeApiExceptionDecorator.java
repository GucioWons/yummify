package com.guciowons.yummify.menu.infrastructure.decorator.rest;

import com.guciowons.yummify.common.exception.infrastructure.in.rest.annotation.HandleDomainExceptions;
import com.guciowons.yummify.menu.application.entry.port.MenuEntryFacadePort;
import com.guciowons.yummify.menu.domain.entity.MenuEntry;
import com.guciowons.yummify.menu.infrastructure.in.rest.exception.MenuDomainExceptionMapper;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
public class MenuEntryFacadeApiExceptionDecorator implements MenuEntryFacadePort {
    private final MenuEntryFacadePort delegate;

    @Override
    @HandleDomainExceptions(handler = MenuDomainExceptionMapper.class)
    public MenuEntry createEntry(UUID sectionId, UUID restaurantId, UUID dishId, BigDecimal price) {
        return delegate.createEntry(sectionId, restaurantId, dishId, price);
    }

    @Override
    @HandleDomainExceptions(handler = MenuDomainExceptionMapper.class)
    public MenuEntry updateEntry(UUID sectionId, UUID id, UUID restaurantId, UUID dishId, BigDecimal price) {
        return delegate.updateEntry(sectionId, id, restaurantId, dishId, price);
    }
}
