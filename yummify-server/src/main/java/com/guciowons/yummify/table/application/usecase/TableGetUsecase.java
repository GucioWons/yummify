package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.table.domain.entity.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableGetUsecase {
    private final RestaurantScopedService<Table> restaurantScopedService;

    public Table get(UUID id) {
        return restaurantScopedService.findById(id);
    }
}
