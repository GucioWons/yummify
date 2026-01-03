package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.auth.exposed.AuthFacadePort;
import com.guciowons.yummify.table.application.dto.mapper.TableMapper;
import com.guciowons.yummify.table.domain.logic.TableValidateService;
import com.guciowons.yummify.table.application.dto.TableDTO;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.repository.TableRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableCreateUsecase {
    private final TableRepository tableRepository;
    private final TableValidateService tableValidateService;
    private final AuthFacadePort authFacadePort;
    private final TableMapper tableMapper;

    @Transactional
    public Table create(TableDTO dto, UUID restaurantId) {
        tableValidateService.checkNameUnique(dto.name(), restaurantId);

        Table table = tableRepository.save(tableMapper.mapToEntity(dto, restaurantId));

        UUID tableUserId = createTableUser(table.getId(), restaurantId);
        table.setUserId(tableUserId);
        return tableRepository.save(table);
    }

    private UUID createTableUser(UUID id, UUID restaurantId) {
        String email = "%s@table.fake".formatted(id);
        String username = id.toString();
        String firstName = "Fake first name";
        String lastName = "Fake last name";
        return authFacadePort.createUser(email, username, firstName, lastName, restaurantId);
    }
}
