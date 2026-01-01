package com.guciowons.yummify.table.application.facade;

import com.guciowons.yummify.auth.OtpDTO;
import com.guciowons.yummify.table.application.mapper.TableMapper;
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

    public TableDTO create(TableDTO dto) {
        Table table = tableCreateUsecase.create(dto);
        return tableMapper.mapToDTO(table);
    }

    public List<TableDTO> getAll() {
        return tableGetAllUsecase.getAll().stream()
                .map(tableMapper::mapToDTO)
                .toList();
    }

    public TableDTO getById(UUID id) {
        Table table = tableGetUsecase.get(id);
        return tableMapper.mapToDTO(table);
    }

    public TableDTO update(UUID id, TableDTO dto) {
        Table table = tableUpdateUsecase.update(id, dto);
        return tableMapper.mapToDTO(table);
    }

    public OtpDTO generateOtp(UUID id) {
        return tableGenerateOtpUsecase.generate(id);
    }
}
