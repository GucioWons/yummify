package com.guciowons.yummify.auth.application.model;

import com.guciowons.yummify.auth.domain.model.User;

public record GenerateOtpCommand(User.ExternalId userId) {
}
