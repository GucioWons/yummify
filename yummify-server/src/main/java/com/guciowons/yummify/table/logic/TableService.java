package com.guciowons.yummify.table.logic;

import com.guciowons.yummify.auth.PublicUserCreateService;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.common.exception.SingleApiErrorException;
import com.guciowons.yummify.common.request.RequestContext;
import com.guciowons.yummify.common.core.service.RestaurantScopedService;
import com.guciowons.yummify.table.TableDTO;
import com.guciowons.yummify.table.data.TableRepository;
import com.guciowons.yummify.table.entity.Table;
import com.guciowons.yummify.table.exception.TableExistsByNameException;
import com.guciowons.yummify.table.exception.TableNotFoundException;
import com.guciowons.yummify.table.mapper.TableMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TableService extends RestaurantScopedService<Table, TableDTO, TableRepository, TableMapper> {
    private final PublicUserCreateService userCreateService;

    public TableService(TableRepository tableRepository, TableMapper tableMapper, PublicUserCreateService userCreateService) {
        super(tableRepository, tableMapper);
        this.userCreateService = userCreateService;
    }

    @Transactional
    public TableDTO create(TableDTO dto) {
        UUID restaurantId = RequestContext.get().getUser().getRestaurantId();
        if (repository.existsByNameAndRestaurantId(dto.getName(), restaurantId)) {
            throw new TableExistsByNameException(dto.getName());
        }

        Table entity = mapper.mapToEntity(dto);
        entity.setRestaurantId(restaurantId);
        repository.save(entity);

        UserRequestDTO userRequest = new UserRequestDTO(
                entity.getId() + "@table.fake",
                entity.getId().toString(),
                "Fake first name",
                "Fake last name",
                Map.of("restaurantId", List.of(restaurantId.toString()))
        );
        UUID tableUserId = userCreateService.createUser(userRequest);
        entity.setUserId(tableUserId);

        return mapper.mapToDTO(entity);
    }

    @Override
    protected SingleApiErrorException getNotFoundException(UUID id) {
        return new TableNotFoundException(id);
    }
}
