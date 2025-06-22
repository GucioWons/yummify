package com.guciowons.yummify.table.logic;

import com.guciowons.yummify.auth.PublicAuthService;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.common.security.logic.TokenService;
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

    public TableDTO create(TableDTO dto) {
        UUID restaurantId = tokenService.getRestaurantId();
        if (tableRepository.existsByNameAndRestaurantId(dto.name(), restaurantId)) {
            throw new TableExistsByNameException(dto.name());
        }

        Table entity = tableRepository.save(tableMapper.mapToEntity(dto));
        UserRequestDTO userRequest = new UserRequestDTO(
                entity.getId() + "@table.fake",
                entity.getId().toString(),
                "Fake first name",
                "Fake last name",
                Map.of("restaurantId", List.of(restaurantId.toString()))
        );
        UUID tableUserId = authService.createUserAndGetId(userRequest);
        entity.setUserId(tableUserId);

        return tableMapper.mapToDTO(tableRepository.save(entity));
    }

    public List<TableDTO> getAll() {
        UUID restaurantId = tokenService.getRestaurantId();
        return tableRepository.findAllByRestaurantId(restaurantId).stream()
                .map(tableMapper::mapToDTO)
                .toList();
    }

    public TableDTO getById(UUID id) {
        UUID restaurantId = tokenService.getRestaurantId();
        return tableRepository.findByIdAndRestaurantId(id, restaurantId)
                .map(tableMapper::mapToDTO)
                .orElseThrow(() -> new TableNotFoundException(id));
    }

    @Transactional
    public TableDTO update(UUID id, TableDTO dto) {
        UUID restaurantId = tokenService.getRestaurantId();
        return tableRepository.findByIdAndRestaurantId(id, restaurantId)
                .map(table -> tableMapper.mapToDTO(tableMapper.mapToUpdateEntity(dto, table)))
                .orElseThrow(() -> new TableNotFoundException(id));
    }
}
