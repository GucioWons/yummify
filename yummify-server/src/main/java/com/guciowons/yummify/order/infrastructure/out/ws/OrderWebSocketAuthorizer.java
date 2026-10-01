package com.guciowons.yummify.order.infrastructure.out.ws;

import com.guciowons.yummify.common.security.application.UserPrincipal;
import com.guciowons.yummify.common.ws.infrstructure.framework.WebSocketSubscriptionAuthorizer;
import com.guciowons.yummify.order.application.service.OrderAccessService;
import com.guciowons.yummify.order.domain.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderWebSocketAuthorizer implements WebSocketSubscriptionAuthorizer {
    private final OrderAccessService orderAccessService;

    @Override
    public boolean supports(String destination) {
        return Arrays.stream(OrderWebSocketDestination.values())
                .anyMatch(orderDestination -> orderDestination.matches(destination));
    }

    @Override
    public boolean isAllowed(String destination, Authentication authentication) {
        OrderWebSocketDestination orderDestination = OrderWebSocketDestination.fromDestination(destination);

        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        return switch (orderDestination) {
            case ORDERS -> checkOrdersAccess(orderDestination, destination, user);
            case ORDER_UPDATES -> checkOrderUpdatesAccess(orderDestination, destination, user);
        };
    }

    private boolean checkOrdersAccess(OrderWebSocketDestination orderDestination, String destination, UserPrincipal user) {
        UUID restaurantId = orderDestination.extractId(destination);
        return user.restaurantId().equals(restaurantId);
    }

    private boolean checkOrderUpdatesAccess(OrderWebSocketDestination orderDestination, String destination, UserPrincipal user) {
        Order.Id id = Order.Id.of(orderDestination.extractId(destination));
        return orderAccessService.canAccess(id, user);
    }
}
