package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.table.application.service.TableLookupService;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.table.application.fixture.TableApplicationFixture.givenGetTableCommand;
import static com.guciowons.yummify.table.domain.fixture.TableDomainFixture.givenTable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetTableUsecaseTest {
    private final TableLookupService tableLookupService = mock(TableLookupService.class);

    private final GetTableUsecase underTest = new GetTableUsecase(tableLookupService);

    @Test
    void shouldGetTable() {
        // given
        var command = givenGetTableCommand();
        var table = givenTable(1);

        when(tableLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(table);

        // when
        var result = underTest.get(command);

        // then
        verify(tableLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());

        assertEquals(table, result);
    }
}