package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.table.application.model.GetAllTablesCommand;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.repository.TableRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Usecase
@RequiredArgsConstructor
public class GetAllTablesUsecase {
    private final TableRepository tableRepository;

    public List<Table> getAll(GetAllTablesCommand command) {
        return tableRepository.findAllByRestaurantId(command.restaurantId());
    }
}
