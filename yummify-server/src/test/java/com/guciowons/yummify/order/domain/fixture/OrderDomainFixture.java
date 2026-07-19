package com.guciowons.yummify.order.domain.fixture;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.entity.Translation;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.order.domain.entity.Order;
import com.guciowons.yummify.order.domain.entity.OrderItem;
import com.guciowons.yummify.order.domain.entity.OrderStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
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

    public static OrderItem givenOrderItem(int seed) {
        return new OrderItem(givenOrderItemId(seed), givenOrderItemDishId(seed), givenOrderItemDishSnapshot(seed), seed);
    }

    public static OrderItem.Id givenOrderItemId(int seed) {
        return OrderItem.Id.of(UUID.nameUUIDFromBytes(("order-item-%s".formatted(seed)).getBytes()));
    }

    public static OrderItem.DishId givenOrderItemDishId(int seed) {
        return OrderItem.DishId.of(UUID.nameUUIDFromBytes(("dish-%s".formatted(seed)).getBytes()));
    }

    public static OrderItem.DishSnapshot givenOrderItemDishSnapshot(int seed) {
        return OrderItem.DishSnapshot.of(givenDishSnapshotName(seed), BigDecimal.valueOf(seed));
    }

    public static TranslatedString givenDishSnapshotName(int seed) {
        return TranslatedString.of(Map.of(Language.EN, Translation.of("dish-%s".formatted(seed))));
    }
}
