package com.guciowons.yummify.menu.infrastructure.decorator.rest;

import com.guciowons.yummify.common.exception.infrastructure.in.rest.annotation.HandleDomainExceptions;
import com.guciowons.yummify.menu.application.port.MenuVersionFacadePort;
import com.guciowons.yummify.menu.domain.entity.MenuVersion;
import com.guciowons.yummify.menu.infrastructure.in.rest.exception.MenuDomainExceptionMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class MenuVersionFacadeApiExceptionDecorator implements MenuVersionFacadePort {
    private final MenuVersionFacadePort delegate;

    @Override
    @HandleDomainExceptions(handler = MenuDomainExceptionMapper.class)
    public MenuVersion create(UUID restaurantId) {
        return delegate.create(restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = MenuDomainExceptionMapper.class)
    public List<MenuVersion> getAllArchived(UUID restaurantId) {
        return delegate.getAllArchived(restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = MenuDomainExceptionMapper.class)
    public MenuVersion getDraft(UUID restaurantId) {
        return delegate.getDraft(restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = MenuDomainExceptionMapper.class)
    public MenuVersion getPublished(UUID restaurantId) {
        return delegate.getPublished(restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = MenuDomainExceptionMapper.class)
    public MenuVersion getArchived(UUID id, UUID restaurantId) {
        return delegate.getArchived(id, restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = MenuDomainExceptionMapper.class)
    public MenuVersion publish(UUID restaurantId) {
        return delegate.publish(restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = MenuDomainExceptionMapper.class)
    public MenuVersion restore(UUID id, UUID restaurantId) {
        return delegate.restore(id, restaurantId);
    }
}
