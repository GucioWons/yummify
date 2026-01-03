package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.table.application.dto.mapper.TableMapper;
import com.guciowons.yummify.table.application.dto.TableDTO;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.repository.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableUpdateUsecase {
    private final TableGetUsecase tableGetUsecase;
    private final TableRepository tableRepository;
    private final TableMapper tableMapper;

    public Table update(UUID id, TableDTO dto, UUID restaurantId) {
        Table table = tableGetUsecase.get(id, restaurantId);
        table = tableMapper.mapToUpdateEntity(dto, table);
        return tableRepository.save(table);
    }
}
