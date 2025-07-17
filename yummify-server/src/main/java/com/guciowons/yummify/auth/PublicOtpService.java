package com.guciowons.yummify.auth;

import java.util.UUID;

public interface PublicOtpService {
    OtpDTO createOtp(UUID userId);
}
