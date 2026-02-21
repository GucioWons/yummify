package com.guciowons.yummify.restaurant.infrastructure.decorator.rest;

import com.guciowons.yummify.common.exception.infrastructure.in.rest.annotation.HandleDomainExceptions;
import com.guciowons.yummify.restaurant.application.model.RestaurantOwner;
import com.guciowons.yummify.restaurant.application.port.RestaurantFacadePort;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.infrastructure.in.rest.exception.RestaurantDomainExceptionMapper;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class RestaurantFacadeApiExceptionDecorator implements RestaurantFacadePort {
    private final RestaurantFacadePort delegate;

    @Override
    @HandleDomainExceptions(handler = RestaurantDomainExceptionMapper.class)
    public Restaurant create(String name, Map<String, String> description, String language, RestaurantOwner owner) {
        return delegate.create(name, description, language, owner);
    }

    @Override
    @HandleDomainExceptions(handler = RestaurantDomainExceptionMapper.class)
    public Restaurant getById(UUID id) {
        return delegate.getById(id);
    }

    @Override
    @HandleDomainExceptions(handler = RestaurantDomainExceptionMapper.class)
    public Restaurant update(UUID id, String name, Map<String, String> description, String language) {
        return delegate.update(id, name, description, language);
    }
}
