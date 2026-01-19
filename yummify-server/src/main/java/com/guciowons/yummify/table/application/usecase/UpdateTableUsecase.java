package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.table.application.model.UpdateTableCommand;
import com.guciowons.yummify.table.application.service.TableLookupService;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.exception.TableExistsByNameException;
import com.guciowons.yummify.table.domain.repository.TableRepository;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class UpdateTableUsecase {
    private final TableLookupService tableLookupService;
    private final TableRepository tableRepository;

    public Table update(UpdateTableCommand command) {
        if (tableRepository.existsByNameAndRestaurantId(command.name(), command.restaurantId())) {
            throw new TableExistsByNameException(command.name());
        }

        Table table = tableLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());
        table.updateDetails(command.name());
        return tableRepository.save(table);
    }
}
