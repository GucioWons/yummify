package com.guciowons.yummify.table.domain.repository;

import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.entity.value.TableId;
import com.guciowons.yummify.table.domain.entity.value.TableName;

import java.util.List;
import java.util.Optional;

public interface TableRepository {
    Table save(Table table);

    Optional<Table> findByIdAndRestaurantId(TableId id, RestaurantId restaurantId);

    List<Table> findAllByRestaurantId(RestaurantId restaurantId);

    boolean existsByNameAndRestaurantId(TableName name, RestaurantId restaurantId);
}
