package com.guciowons.yummify.auth.application.model;

import com.guciowons.yummify.auth.domain.model.value.UserId;

public record GenerateOtpCommand(UserId userId) {
}
