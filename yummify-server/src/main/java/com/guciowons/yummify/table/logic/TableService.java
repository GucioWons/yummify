package com.guciowons.yummify.table.logic;

import com.guciowons.yummify.auth.PublicUserCreateService;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.table.dto.TableDTO;
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
    private final PublicUserCreateService userCreateService;

    @Transactional
    public TableDTO create(TableDTO dto) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        if (tableRepository.existsByNameAndRestaurantId(dto.getName(), restaurantId)) {
            throw new TableExistsByNameException(dto.getName());
        }

        Table entity = tableMapper.mapToEntity(dto);
        entity.setRestaurantId(restaurantId);
        entity = tableRepository.save(entity);

        UserRequestDTO userRequest = new UserRequestDTO(
                entity.getId() + "@table.fake",
                entity.getId().toString(),
                "Fake first name",
                "Fake last name",
                Map.of("restaurantId", List.of(restaurantId.toString()))
        );
        UUID tableUserId = userCreateService.createUser(userRequest);
        entity.setUserId(tableUserId);

        return tableMapper.mapToDTO(entity);
    }

    public List<TableDTO> getAll() {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return tableRepository.findAllByRestaurantId(restaurantId).stream()
                .map(tableMapper::mapToDTO)
                .toList();
    }

    public TableDTO getById(UUID id) {
        return tableMapper.mapToDTO(getEntityById(id));
    }

    @Transactional
    public TableDTO update(UUID id, TableDTO dto) {
        Table updatedEntity = tableMapper.mapToUpdateEntity(dto, getEntityById(id));
        return tableMapper.mapToDTO(updatedEntity);
    }

    private Table getEntityById(UUID id) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        return tableRepository.findByIdAndRestaurantId(id, restaurantId)
                .orElseThrow(() -> new TableNotFoundException(id));
    }
}
