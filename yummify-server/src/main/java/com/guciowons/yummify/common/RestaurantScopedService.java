package com.guciowons.yummify.common;

import com.guciowons.yummify.common.core.entity.BaseEntity;
import com.guciowons.yummify.common.core.entity.RestaurantScoped;
import com.guciowons.yummify.common.core.repository.RestaurantScopedRepository;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.request.RequestContext;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@RequiredArgsConstructor
public class RestaurantScopedService<T extends RestaurantScoped & BaseEntity> {
    private final RestaurantScopedRepository<T> repository;
    private final Function<UUID, SingleApiErrorException> getNotFoundException;

    public T create(T entity) {
        entity.setRestaurantId(restaurantId());
        return entity;
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public List<T> findAll() {
        return repository.findAllByRestaurantId(restaurantId());
    }

    public T findById(UUID id) {
        return repository.findByIdAndRestaurantId(id, restaurantId())
                .orElseThrow(() -> getNotFoundException.apply(id));
    }

    private UUID restaurantId() {
        return RequestContext.get().getUser().getRestaurantId();
    }
}
