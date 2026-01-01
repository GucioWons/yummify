package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.table.application.mapper.TableMapper;
import com.guciowons.yummify.table.application.dto.TableDTO;
import com.guciowons.yummify.table.domain.entity.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableUpdateUsecase {
    private final RestaurantScopedService<Table> restaurantScopedService;
    private final TableMapper tableMapper;

    public Table update(UUID id, TableDTO dto) {
        Table updatedTable = tableMapper.mapToUpdateEntity(dto, restaurantScopedService.findById(id));
        return restaurantScopedService.save(updatedTable);
    }
}
