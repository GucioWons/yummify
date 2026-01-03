package com.guciowons.yummify.table.domain.repository;

import com.guciowons.yummify.common.core.domain.repository.RestaurantScopedRepository;
import com.guciowons.yummify.table.domain.entity.Table;

import java.util.UUID;

public interface TableRepository extends RestaurantScopedRepository<Table> {
    boolean existsByNameAndRestaurantId(String name, UUID restaurantId);
}
