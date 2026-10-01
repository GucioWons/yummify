package com.guciowons.yummify.order.infrastructure.out.ws;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderWebSocketDestination {
    ORDERS("/topic/restaurants/%s/orders"),
    ORDER_UPDATES("/topic/orders/%s");

    private final String pattern;

    public boolean matches(String destination) {
        return destination.matches(pattern.formatted("[^/]+"));
    }

    public String build(String... args) {
        return pattern.formatted((Object[]) args);
    }
}
