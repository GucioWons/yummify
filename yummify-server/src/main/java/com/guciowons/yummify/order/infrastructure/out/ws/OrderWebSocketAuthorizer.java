package com.guciowons.yummify.order.infrastructure.out.ws;

import com.guciowons.yummify.common.ws.infrstructure.framework.WebSocketSubscriptionAuthorizer;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class OrderWebSocketAuthorizer implements WebSocketSubscriptionAuthorizer {

    @Override
    public boolean supports(String destination) {
        return Arrays.stream(OrderWebSocketDestination.values())
                .anyMatch(orderDestination -> orderDestination.matches(destination));
    }

    @Override
    public boolean isAllowed(String destination, Authentication authentication) {
        return true;
    }
}
