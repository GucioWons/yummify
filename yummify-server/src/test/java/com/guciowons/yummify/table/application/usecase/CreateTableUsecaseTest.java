package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.auth.AuthFacadePort;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.exception.TableExistsByNameException;
import com.guciowons.yummify.table.domain.repository.TableRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.guciowons.yummify.table.application.fixture.TableApplicationFixture.givenCreateTableCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateTableUsecaseTest {
    private final TableRepository tableRepository = mock(TableRepository.class);
    private final AuthFacadePort authFacadePort = mock(AuthFacadePort.class);

    private final CreateTableUsecase underTest = new CreateTableUsecase(tableRepository, authFacadePort);

    @Test
    void shouldCreateTable() {
        // given
        var command = givenCreateTableCommand();
        var userId = UUID.nameUUIDFromBytes("user".getBytes());

        when(tableRepository.existsByNameAndRestaurantId(command.name(), command.restaurantId())).thenReturn(false);
        when(authFacadePort.createUser(any(), any(), any(), any(), any(), eq(false))).thenReturn(userId);

        // when
        var result = underTest.create(command);

        // then
        verify(tableRepository).existsByNameAndRestaurantId(command.name(), command.restaurantId());
        verify(authFacadePort).createUser(any(), any(), any(), any(), any(), eq(false));
        verify(tableRepository).save(any(Table.class));

        assertThat(result.getId()).isNotNull();
        assertThat(result.getUserId()).isEqualTo(Table.UserId.of(userId));
        assertThat(result.getName()).isEqualTo(command.name());
    }

    @Test
    void shouldNotCreateTableAndThrowException_WhenTableExistsByName() {
        // given
        var command = givenCreateTableCommand();

        when(tableRepository.existsByNameAndRestaurantId(command.name(), command.restaurantId())).thenReturn(true);

        // when
        assertThatThrownBy(() -> underTest.create(command)).isInstanceOf(TableExistsByNameException.class);

        // then
        verify(tableRepository).existsByNameAndRestaurantId(command.name(), command.restaurantId());
        verify(authFacadePort, never()).createUser(any(), any(), any(), any(), any(), anyBoolean());
        verify(tableRepository, never()).save(any(Table.class));
    }
}