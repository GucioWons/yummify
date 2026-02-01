package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.table.domain.repository.TableRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.guciowons.yummify.table.application.fixture.TableApplicationFixture.givenGetAllTablesCommand;
import static com.guciowons.yummify.table.domain.fixture.TableDomainFixture.givenTable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetAllTablesUsecaseTest {
    private final TableRepository tableRepository = mock(TableRepository.class);

    private final GetAllTablesUsecase underTest = new GetAllTablesUsecase(tableRepository);

    @Test
    void shouldGetAllTables() {
        // given
        var command = givenGetAllTablesCommand();
        var tables = List.of(givenTable(1), givenTable(2), givenTable(3));

        when(tableRepository.findAllByRestaurantId(command.restaurantId())).thenReturn(tables);

        // when
        var result = underTest.getAll(command);

        // then
        verify(tableRepository).findAllByRestaurantId(command.restaurantId());

        assertThat(result).isEqualTo(tables);
    }

}