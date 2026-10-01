package com.guciowons.yummify.common.ws.infrstructure.framework;

import org.springframework.security.core.Authentication;

public interface WebSocketSubscriptionAuthorizer {
    boolean supports(String destination);

    boolean isAllowed(String destination, Authentication authentication);
}
