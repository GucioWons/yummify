package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.auth.AuthFacadePort;
import com.guciowons.yummify.table.application.service.TableLookupService;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.table.application.fixture.TableApplicationFixture.givenGenerateTableOtpCommand;
import static com.guciowons.yummify.table.domain.fixture.TableDomainFixture.givenTable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenerateTableOtpUsecaseTest {
    private final TableLookupService tableLookupService = mock(TableLookupService.class);
    private final AuthFacadePort authFacadePort = mock(AuthFacadePort.class);

    private final GenerateTableOtpUsecase underTest = new GenerateTableOtpUsecase(tableLookupService, authFacadePort);

    @Test
    void shouldGenerateTableOtpUsecase() {
        // given
        var command = givenGenerateTableOtpCommand();
        var table = givenTable(1);
        var otp = "otp";

        when(tableLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId())).thenReturn(table);
        when(authFacadePort.generateOtp(table.getUserId().value())).thenReturn(otp);

        // when
        var result = underTest.generate(command);

        // then
        verify(tableLookupService).getByIdAndRestaurantId(command.id(), command.restaurantId());
        verify(authFacadePort).generateOtp(table.getUserId().value());

        assertEquals(otp, result);
    }
}