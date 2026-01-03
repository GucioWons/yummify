package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.auth.exposed.AuthFacadePort;
import com.guciowons.yummify.table.domain.entity.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableGenerateOtpUsecase {
    private final TableGetUsecase tableGetUsecase;
    private final AuthFacadePort authFacadePort;

    public String generate(UUID id, UUID restaurantId) {
        Table table = tableGetUsecase.get(id, restaurantId);
        return authFacadePort.generateOtp(table.getUserId());
    }
}
