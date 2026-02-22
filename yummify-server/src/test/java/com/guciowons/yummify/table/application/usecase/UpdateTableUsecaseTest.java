package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.table.application.service.TableLookupService;
import com.guciowons.yummify.table.domain.exception.TableExistsByNameException;
import com.guciowons.yummify.table.domain.repository.TableRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.table.application.fixture.TableApplicationFixture.givenUpdateTableCommand;
import static com.guciowons.yummify.table.domain.fixture.TableDomainFixture.givenTable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

class UpdateTableUsecaseTest {
    private final TableLookupService tableLookupService = mock(TableLookupService.class);
    private final TableRepository tableRepository = mock(TableRepository.class);

    private final UpdateTableUsecase underTest = new UpdateTableUsecase(tableLookupService, tableRepository);

    @Test
    void shouldUpdateTable() {
        // given
        var command = givenUpdateTableCommand();
        var table = givenTable(2);

        when(tableRepository.existsByNameAndRestaurantId(command.name(), command.restaurantId())).thenReturn(false);
        when(tableLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(table);

        // when
        var result = underTest.update(command);

        // then
        verify(tableRepository).existsByNameAndRestaurantId(command.name(), command.restaurantId());
        verify(tableLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());
        verify(tableRepository).save(table);

        assertThat(result.getId()).isEqualTo(table.getId());
        assertThat(result.getUserId()).isEqualTo(table.getUserId());
        assertThat(result.getName()).isEqualTo(command.name());
    }

    @Test
    void shouldNotUpdateTableAndThrowException_WhenTableExistsByName() {
        // given
        var command = givenUpdateTableCommand();

        when(tableRepository.existsByNameAndRestaurantId(command.name(), command.restaurantId())).thenReturn(true);

        // when
        assertThatThrownBy(() -> underTest.update(command)).isInstanceOf(TableExistsByNameException.class);

        // then
        verify(tableRepository).existsByNameAndRestaurantId(command.name(), command.restaurantId());
        verify(tableLookupService, never()).getByIdAndRestaurantId(any(), any());
        verify(tableRepository, never()).save(any());
    }
}