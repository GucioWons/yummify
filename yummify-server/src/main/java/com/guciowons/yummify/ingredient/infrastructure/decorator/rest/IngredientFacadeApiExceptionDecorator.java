package com.guciowons.yummify.ingredient.infrastructure.decorator.rest;

import com.guciowons.yummify.common.exception.infrastructure.in.rest.annotation.HandleDomainExceptions;
import com.guciowons.yummify.ingredient.application.port.IngredientFacadePort;
import com.guciowons.yummify.ingredient.domain.entity.Ingredient;
import com.guciowons.yummify.ingredient.infrastructure.in.rest.exception.IngredientDomainExceptionMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class IngredientFacadeApiExceptionDecorator implements IngredientFacadePort {
    private final IngredientFacadePort delegate;

    @Override
    @HandleDomainExceptions(handler = IngredientDomainExceptionMapper.class)
    public Ingredient create(UUID restaurantId, Map<String, String> name) {
        return delegate.create(restaurantId, name);
    }

    @Override
    @HandleDomainExceptions(handler = IngredientDomainExceptionMapper.class)
    public List<Ingredient> getAll(UUID restaurantId) {
        return delegate.getAll(restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = IngredientDomainExceptionMapper.class)
    public Ingredient getById(UUID id, UUID restaurantId) {
        return delegate.getById(id, restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = IngredientDomainExceptionMapper.class)
    public Ingredient update(UUID id, UUID restaurantId, Map<String, String> name) {
        return delegate.update(id, restaurantId, name);
    }
}
