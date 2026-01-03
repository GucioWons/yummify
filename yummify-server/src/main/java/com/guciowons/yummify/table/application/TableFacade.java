package com.guciowons.yummify.table.application;

import com.guciowons.yummify.table.application.dto.TableOtpDTO;
import com.guciowons.yummify.table.application.dto.mapper.TableMapper;
import com.guciowons.yummify.table.application.usecase.*;
import com.guciowons.yummify.table.application.dto.TableDTO;
import com.guciowons.yummify.table.domain.entity.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableFacade {
    private final TableCreateUsecase tableCreateUsecase;
    private final TableGetAllUsecase tableGetAllUsecase;
    private final TableGetUsecase tableGetUsecase;
    private final TableUpdateUsecase tableUpdateUsecase;
    private final TableGenerateOtpUsecase tableGenerateOtpUsecase;
    private final TableMapper tableMapper;

    public TableDTO create(TableDTO dto, UUID restaurantId) {
        Table table = tableCreateUsecase.create(dto, restaurantId);
        return tableMapper.mapToDTO(table);
    }

    public List<TableDTO> getAll(UUID restaurantId) {
        return tableGetAllUsecase.getAll(restaurantId).stream()
                .map(tableMapper::mapToDTO)
                .toList();
    }

    public TableDTO getById(UUID id, UUID restaurantId) {
        Table table = tableGetUsecase.get(id, restaurantId);
        return tableMapper.mapToDTO(table);
    }

    public TableDTO update(UUID id, TableDTO dto, UUID restaurantId) {
        Table table = tableUpdateUsecase.update(id, dto, restaurantId);
        return tableMapper.mapToDTO(table);
    }

    public TableOtpDTO generateOtp(UUID id, UUID restaurantId) {
        return new TableOtpDTO(tableGenerateOtpUsecase.generate(id, restaurantId), id);
    }
}
