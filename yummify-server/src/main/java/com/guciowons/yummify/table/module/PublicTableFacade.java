package com.guciowons.yummify.table.module;

import com.guciowons.yummify.common.core.application.annotation.Facade;
import com.guciowons.yummify.table.PublicTableFacadePort;
import com.guciowons.yummify.table.application.service.TableLookupService;
import com.guciowons.yummify.table.domain.entity.Table;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Facade
@RequiredArgsConstructor
public class PublicTableFacade implements PublicTableFacadePort {
    private final TableLookupService tableLookupService;

    public UUID getTableIdByUserId(UUID userId, UUID restaurantId) {
        return tableLookupService.getByUserIdAndRestaurantId(Table.UserId.of(userId), Table.RestaurantId.of(restaurantId))
                .getId()
                .value();
    }
}
