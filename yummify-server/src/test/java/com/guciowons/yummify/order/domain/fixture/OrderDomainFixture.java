package com.guciowons.yummify.order.domain.fixture;

import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDomainFixture {
    public static Order givenOrder(int seed) {
        return new Order(
                givenOrderId(seed),
                givenOrderRestaurantId(seed),
                givenOrderTableId(seed),
                OrderStatus.NEW
        );
    }

    public static Order.Id givenOrderId(int seed) {
        return Order.Id.of(UUID.nameUUIDFromBytes(("order-%s".formatted(seed)).getBytes()));
    }

    public static Order.RestaurantId givenOrderRestaurantId(int seed) {
        return Order.RestaurantId.of(UUID.nameUUIDFromBytes(("restaurant-%s".formatted(seed)).getBytes()));
    }

    public static Order.TableId givenOrderTableId(int seed) {
        return Order.TableId.of(UUID.nameUUIDFromBytes(("table-%s".formatted(seed)).getBytes()));
    }
}
