package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.table.domain.entity.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TableGetAllUsecase {
    private final RestaurantScopedService<Table> restaurantScopedService;

    public List<Table> getAll() {
        return restaurantScopedService.findAll();
    }
}
