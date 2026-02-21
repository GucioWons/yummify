package com.guciowons.yummify.dish.infrastructure.decorator.rest;

import com.guciowons.yummify.common.exception.infrastructure.in.rest.annotation.HandleDomainExceptions;
import com.guciowons.yummify.dish.application.port.DishFacadePort;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.infrastructure.in.rest.exception.DishDomainExceptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class DishFacadeApiExceptionDecorator implements DishFacadePort {
    private final DishFacadePort delegate;

    @Override
    @HandleDomainExceptions(handler = DishDomainExceptionMapper.class)
    public Dish create(UUID restaurantId, Map<String, String> name, Map<String, String> description, List<UUID> ingredientIds) {
        return delegate.create(restaurantId, name, description, ingredientIds);
    }

    @Override
    @HandleDomainExceptions(handler = DishDomainExceptionMapper.class)
    public List<Dish> getAll(UUID restaurantId) {
        return delegate.getAll(restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = DishDomainExceptionMapper.class)
    public Dish getById(UUID id, UUID restaurantId) {
        return delegate.getById(id, restaurantId);
    }

    @Override
    @HandleDomainExceptions(handler = DishDomainExceptionMapper.class)
    public Dish update(UUID id, UUID restaurantId, Map<String, String> name, Map<String, String> description, List<UUID> ingredientIds) {
        return delegate.update(id, restaurantId, name, description, ingredientIds);
    }

    @Override
    @HandleDomainExceptions(handler = DishDomainExceptionMapper.class)
    public Dish.ImageId updateImage(UUID id, UUID restaurantId, MultipartFile image) {
        return delegate.updateImage(id, restaurantId, image);
    }
}
