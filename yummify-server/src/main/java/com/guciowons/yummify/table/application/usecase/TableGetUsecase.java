package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.table.application.model.GetTableCommand;
import com.guciowons.yummify.table.application.service.TableLookupService;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.exception.TableNotFoundException;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class TableGetUsecase {
    private final TableLookupService tableLookupService;

    public Table get(GetTableCommand command) throws TableNotFoundException {
        return tableLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());
    }
}
