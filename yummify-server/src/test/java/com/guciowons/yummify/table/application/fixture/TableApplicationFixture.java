package com.guciowons.yummify.table.application.fixture;

import com.guciowons.yummify.table.application.model.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.guciowons.yummify.table.domain.fixture.TableDomainFixture.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TableApplicationFixture {
    public static CreateTableCommand givenCreateTableCommand() {
        return new CreateTableCommand(givenTableRestaurantId(1), givenTableName(1), givenTableCapacity(1));
    }

    public static GenerateTableOtpCommand givenGenerateTableOtpCommand() {
        return new GenerateTableOtpCommand(givenTableId(1), givenTableRestaurantId(1));
    }

    public static GetAllTablesCommand givenGetAllTablesCommand() {
        return new GetAllTablesCommand(givenTableRestaurantId(1));
    }

    public static GetTableCommand givenGetTableCommand() {
        return new GetTableCommand(givenTableId(1), givenTableRestaurantId(1));
    }

    public static UpdateTableCommand givenUpdateTableCommand() {
        return new UpdateTableCommand(givenTableId(1), givenTableRestaurantId(1), givenTableName(1), givenTableCapacity(1));
    }
}
