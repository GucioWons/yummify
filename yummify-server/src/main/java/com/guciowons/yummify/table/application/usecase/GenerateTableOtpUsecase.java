package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.auth.AuthFacadePort;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.table.application.model.GenerateTableOtpCommand;
import com.guciowons.yummify.table.application.service.TableLookupService;
import com.guciowons.yummify.table.domain.entity.Table;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class GenerateTableOtpUsecase {
    private final TableLookupService tableLookupService;
    private final AuthFacadePort authFacadePort;

    public String generate(GenerateTableOtpCommand command) throws DomainException {
        Table table = tableLookupService.getByIdAndRestaurantId(command.id(), command.restaurantId());
        return authFacadePort.generateOtp(table.getUserId().value());
    }
}
