package com.guciowons.yummify.table.infrastructure.repository;

import com.guciowons.yummify.common.core.repository.RestaurantScopedRepository;
import com.guciowons.yummify.table.domain.entity.Table;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TableRepository extends RestaurantScopedRepository<Table> {
    boolean existsByNameAndRestaurantId(String name, UUID restaurantId);
}
