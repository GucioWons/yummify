package com.guciowons.yummify.table.application.usecase;

import com.guciowons.yummify.auth.OtpDTO;
import com.guciowons.yummify.auth.PublicOtpService;
import com.guciowons.yummify.common.RestaurantScopedService;
import com.guciowons.yummify.table.domain.entity.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableGenerateOtpUsecase {
    private final RestaurantScopedService<Table> restaurantScopedService;
    private final PublicOtpService otpService;

    public OtpDTO generate(UUID id) {
        Table table = restaurantScopedService.findById(id);
        return otpService.createOtp(table.getUserId());
    }
}
