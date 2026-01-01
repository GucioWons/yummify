package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.auth.PublicUserCreateService;
import com.guciowons.yummify.auth.UserRequestDTO;
import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.table.application.mapper.TableMapper;
import com.guciowons.yummify.table.domain.logic.TableValidateService;
import com.guciowons.yummify.table.application.dto.TableDTO;
import com.guciowons.yummify.table.domain.entity.Table;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableCreateUsecase {
    private final RestaurantScopedService<Table> restaurantScopedService;
    private final TableValidateService tableValidateService;
    private final PublicUserCreateService userCreateService;
    private final TableMapper tableMapper;

    @Transactional
    public Table create(TableDTO dto) {
        UUID restaurantId = restaurantScopedService.restaurantId();
        tableValidateService.checkNameUnique(dto.name(), restaurantId);

        Table table = restaurantScopedService.create(tableMapper.mapToEntity(dto));
        table = restaurantScopedService.save(table);

        UUID tableUserId = createTableUser(table.getId(), restaurantId);
        table.setUserId(tableUserId);
        return restaurantScopedService.save(table);
    }

    private UUID createTableUser(UUID id, UUID restaurantId) {
        UserRequestDTO userRequest = new UserRequestDTO(
                id + "@table.fake",
                id.toString(),
                "Fake first name",
                "Fake last name",
                Map.of("restaurantId", List.of(restaurantId.toString()))
        );
        return userCreateService.createUser(userRequest);
    }
}
