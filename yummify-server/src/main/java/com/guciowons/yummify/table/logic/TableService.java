package com.guciowons.yummify.table.logic;

import com.guciowons.yummify.auth.PublicAuthService;
import com.guciowons.yummify.common.security.logic.TokenService;
import com.guciowons.yummify.table.TableCreateDTO;
import com.guciowons.yummify.table.TableDTO;
import com.guciowons.yummify.table.data.TableRepository;
import com.guciowons.yummify.table.entity.Table;
import com.guciowons.yummify.table.exception.TableExistsByNameException;
import com.guciowons.yummify.table.exception.TableNotFoundException;
import com.guciowons.yummify.table.mapper.TableMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;
    private final TableMapper tableMapper;
    private final TokenService tokenService;
    private final PublicAuthService authService;

    @Transactional
    public TableDTO create(TableCreateDTO dto) {
        UUID restaurantId = tokenService.getRestaurantId();
        if (tableRepository.existsByNameAndRestaurantId(dto.name(), restaurantId)) {
            throw new TableExistsByNameException(dto.name());
        }

        Table entity = tableRepository.save(tableMapper.mapToEntity(dto));
        dto.user().setAttributes(Map.of("restaurantId", entity.getId().toString()));
        UUID tableUserId = authService.createUserAndGetId(dto.user());
        entity.setUserId(tableUserId);
        entity.setRestaurantId(restaurantId);

        return tableMapper.mapToDTO(entity);
    }

    public List<TableDTO> getAll() {
        UUID restaurantId = tokenService.getRestaurantId();
        return tableRepository.findAllByRestaurantId(restaurantId).stream()
                .map(tableMapper::mapToDTO)
                .toList();
    }

    public TableDTO getById(UUID id) {
        UUID restaurantId = tokenService.getRestaurantId();
        Table table = tableRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new TableNotFoundException(id));
        return tableMapper.mapToDTO(table);
    }
}
