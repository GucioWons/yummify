package com.guciowons.yummify.table.application.service;

import com.guciowons.yummify.common.core.application.annotation.ApplicationService;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.exception.TableNotFoundException;
import com.guciowons.yummify.table.domain.repository.TableRepository;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class TableLookupService {
    private final TableRepository tableRepository;

    public Table getByIdAndRestaurantId(Table.Id id, Table.RestaurantId restaurantId) {
        return tableRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> TableNotFoundException.byId(id));
    }

    public Table getByUserIdAndRestaurantId(Table.UserId userId, Table.RestaurantId restaurantId) {
        return tableRepository.findByUserIdAndRestaurantId(userId, restaurantId)
                .orElseThrow(() -> TableNotFoundException.byUserId(userId));
    }
}
