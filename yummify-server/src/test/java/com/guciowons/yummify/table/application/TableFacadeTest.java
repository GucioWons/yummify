package com.guciowons.yummify.table.application;

import com.guciowons.yummify.table.application.model.mapper.TableCommandMapper;
import com.guciowons.yummify.table.application.usecase.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.guciowons.yummify.restaurant.domain.fixture.RestaurantDomainFixture.givenRestaurantId;
import static com.guciowons.yummify.table.application.fixture.TableApplicationFixture.*;
import static com.guciowons.yummify.table.domain.fixture.TableDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TableFacadeTest {
    private final CreateTableUsecase createTableUsecase = mock(CreateTableUsecase.class);
    private final GetAllTablesUsecase getAllTablesUsecase = mock(GetAllTablesUsecase.class);
    private final GetTableUsecase getTableUsecase = mock(GetTableUsecase.class);
    private final UpdateTableUsecase updateTableUsecase = mock(UpdateTableUsecase.class);
    private final GenerateTableOtpUsecase generateTableOtpUsecase = mock(GenerateTableOtpUsecase.class);
    private final TableCommandMapper tableCommandMapper = mock(TableCommandMapper.class);

    private final TableFacade underTest = new TableFacade(
            createTableUsecase,
            getAllTablesUsecase,
            getTableUsecase,
            updateTableUsecase,
            generateTableOtpUsecase,
            tableCommandMapper
    );

    @Test
    void shouldCreateTable() {
        // given
        var restaurantId = givenRestaurantId(1).value();
        var name = givenTableName(1).value();
        var command = givenCreateTableCommand();
        var table = givenTable(1);

        when(tableCommandMapper.toCreateTableCommand(restaurantId, name)).thenReturn(command);
        when(createTableUsecase.create(command)).thenReturn(table);

        // when
        var result = underTest.create(restaurantId, name);

        // then
        verify(tableCommandMapper).toCreateTableCommand(restaurantId, name);
        verify(createTableUsecase).create(command);

        assertThat(result).isEqualTo(table);
    }

    @Test
    void shouldGetAllTables() {
        // given
        var restaurantId = givenRestaurantId(1).value();
        var command = givenGetAllTablesCommand();
        var tables = List.of(givenTable(1), givenTable(2), givenTable(3));

        when(tableCommandMapper.toGetAllTablesCommand(restaurantId)).thenReturn(command);
        when(getAllTablesUsecase.getAll(command)).thenReturn(tables);

        // when
        var result = underTest.getAll(restaurantId);

        // then
        verify(tableCommandMapper).toGetAllTablesCommand(restaurantId);
        verify(getAllTablesUsecase).getAll(command);

        assertThat(result).isEqualTo(tables);
    }

    @Test
    void shouldGetTable() {
        // given
        var table = givenTable(1);
        var tableId = table.getId().value();
        var restaurantId = table.getRestaurantId().value();
        var command = givenGetTableCommand();

        when(tableCommandMapper.toGetTableCommand(tableId, restaurantId)).thenReturn(command);
        when(getTableUsecase.get(command)).thenReturn(table);

        // when
        var result = underTest.getById(tableId, restaurantId);

        // then
        verify(tableCommandMapper).toGetTableCommand(tableId, restaurantId);
        verify(getTableUsecase).get(command);

        assertThat(result).isEqualTo(table);
    }

    @Test
    void shouldUpdateTable() {
        // given
        var table = givenTable(1);
        var tableId = table.getId().value();
        var restaurantId = table.getRestaurantId().value();
        var name = givenTableName(1).value();
        var command = givenUpdateTableCommand();

        when(tableCommandMapper.toUpdateTableCommand(tableId, restaurantId, name)).thenReturn(command);
        when(updateTableUsecase.update(command)).thenReturn(table);

        // when
        var result = underTest.update(tableId, restaurantId, name);

        // then
        verify(tableCommandMapper).toUpdateTableCommand(tableId, restaurantId, name);
        verify(updateTableUsecase).update(command);

        assertThat(result).isEqualTo(table);
    }

    @Test
    void shouldGenerateTableOtp() {
        // given
        var tableId = givenTableId(1).value();
        var restaurantId = givenRestaurantId(1).value();
        var command = givenGenerateTableOtpCommand();
        var otp = "otp";

        when(tableCommandMapper.toGenerateTableOtpCommand(tableId, restaurantId)).thenReturn(command);
        when(generateTableOtpUsecase.generate(command)).thenReturn(otp);

        // when
        var result = underTest.generateOtp(tableId, restaurantId);

        // then
        verify(tableCommandMapper).toGenerateTableOtpCommand(tableId, restaurantId);
        verify(generateTableOtpUsecase).generate(command);

        assertThat(result.otp()).isEqualTo(otp);
        assertThat(result.tableId()).isEqualTo(tableId);
    }
}