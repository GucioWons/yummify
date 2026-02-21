package com.guciowons.yummify.menu.infrastructure.decorator.rest;

import com.guciowons.yummify.common.exception.infrastructure.in.rest.annotation.HandleDomainExceptions;
import com.guciowons.yummify.menu.application.port.MenuSectionFacadePort;
import com.guciowons.yummify.menu.domain.entity.MenuSection;
import com.guciowons.yummify.menu.domain.snapshot.MenuEntrySnapshot;
import com.guciowons.yummify.menu.infrastructure.in.rest.exception.MenuDomainExceptionMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class MenuSectionFacadeApiExceptionDecorator implements MenuSectionFacadePort {
    private final MenuSectionFacadePort delegate;

    @Override
    @HandleDomainExceptions(handler = MenuDomainExceptionMapper.class)
    public MenuSection create(UUID restaurantId, Map<String, String> name) {
        return delegate.create(restaurantId, name);
    }

    @Override
    @HandleDomainExceptions(handler = MenuDomainExceptionMapper.class)
    public MenuSection updateEntries(UUID id, UUID restaurantId, List<MenuEntrySnapshot> entrySnapshots) {
        return delegate.updateEntries(id, restaurantId, entrySnapshots);
    }

    @Override
    @HandleDomainExceptions(handler = MenuDomainExceptionMapper.class)
    public MenuSection updateName(UUID id, UUID restaurantId, Map<String, String> name) {
        return delegate.updateName(id, restaurantId, name);
    }

    @Override
    @HandleDomainExceptions(handler = MenuDomainExceptionMapper.class)
    public List<MenuSection> updatePosition(UUID id, UUID restaurantId, Integer position) {
        return delegate.updatePosition(id, restaurantId, position);
    }
}
