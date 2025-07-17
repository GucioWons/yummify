package com.guciowons.yummify.table.logic;

import com.guciowons.yummify.auth.OtpDTO;
import com.guciowons.yummify.auth.PublicOtpService;
import com.guciowons.yummify.common.security.logic.TokenService;
import com.guciowons.yummify.table.data.TableRepository;
import com.guciowons.yummify.table.entity.Table;
import com.guciowons.yummify.table.exception.TableNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TableAuthService {
    private final TableRepository tableRepository;
    private final TokenService tokenService;
    private final PublicOtpService otpService;

    public OtpDTO generateOtp(UUID id) {
        UUID restaurantId = tokenService.getRestaurantId();
        UUID tableUserId = tableRepository.findByIdAndRestaurantId(id, restaurantId)
                .map(Table::getUserId)
                .orElseThrow(() -> new TableNotFoundException(id));
        return otpService.createOtp(tableUserId);
    }
}
