package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.auth.AuthFacadePort;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.table.application.model.CreateTableCommand;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.exception.TableExistsByNameException;
import com.guciowons.yummify.table.domain.repository.TableRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateTableUsecase {
    private final TableRepository tableRepository;
    private final AuthFacadePort authFacadePort;

    @Transactional
    public Table create(CreateTableCommand command) throws TableExistsByNameException {
        if (tableRepository.existsByNameAndRestaurantId(command.name(), command.restaurantId())) {
            throw new TableExistsByNameException(command.name());
        }

        Table table = Table.create(command.restaurantId(), command.name(), command.capacity());
        table.changeUser(createTableUser(table.getId(), table.getRestaurantId()));

        tableRepository.save(table);

        return table;
    }

    private Table.UserId createTableUser(Table.Id id, Table.RestaurantId restaurantId) {
        String email = "%s@table.fake".formatted(id.value());
        String username = id.value().toString();
        String firstName = "Fake first name";
        String lastName = "Fake last name";

        return Table.UserId.of(
                authFacadePort.createUser(email, username, firstName, lastName, restaurantId.value(), false)
        );
    }
}
