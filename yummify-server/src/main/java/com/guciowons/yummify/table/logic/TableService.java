package com.guciowons.yummify.table.logic;

import com.guciowons.yummify.table.TableDTO;
import com.guciowons.yummify.table.data.TableRepository;
import com.guciowons.yummify.table.entity.Table;
import com.guciowons.yummify.table.mapper.TableMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final TableMapper tableMapper;

    public TableDTO create(TableDTO dto) {
        Table table = tableRepository.save(tableMapper.mapToEntity(dto));
        return tableMapper.mapToDTO(table);
    }
}
