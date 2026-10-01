package com.guciowons.yummify.order.infrastructure.out.ws;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.UUID;

@AllArgsConstructor
public enum OrderWebSocketDestination {
    ORDERS("/topic/restaurants/%s/orders"),
    ORDER_UPDATES("/topic/orders/%s");

    private final String pattern;

    public boolean matches(String destination) {
        return destination.matches(toRegex());
    }

    public String build(String... args) {
        return pattern.formatted((Object[]) args);
    }

    public static OrderWebSocketDestination fromDestination(String destination) {
        return Arrays.stream(values())
                .filter(type -> type.matches(destination))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public UUID extractId(String destination) {
        return UUID.fromString(destination.replaceFirst(toRegex(), "$1"));
    }

    private String toRegex() {
        return pattern.replace("%s", "([^/]+)");
    }
}
