package com.guciowons.yummify.table.domain.repository;

import com.guciowons.yummify.table.domain.entity.Table;

import java.util.List;
import java.util.Optional;

public interface TableRepository {
    void save(Table table);

    Optional<Table> findByIdAndRestaurantId(Table.Id id, Table.RestaurantId restaurantId);

    List<Table> findAllByRestaurantId(Table.RestaurantId restaurantId);

    boolean existsByNameAndRestaurantId(Table.Name name, Table.RestaurantId restaurantId);
}
