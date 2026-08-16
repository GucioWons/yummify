package com.guciowons.yummify.order.application.fixture;

import com.guciowons.yummify.order.application.command.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderApplicationFixture {
    public static CreateOrderCommand givenCreateOrderCommand() {
        return new CreateOrderCommand(givenUserId(), givenOrderRestaurantId(1));
    }

    public static AddOrderItemCommand givenAddOrderItemCommand() {
        return new AddOrderItemCommand(givenUserId(), givenOrderRestaurantId(1), givenOrderItemDishId(1), 1);
    }

    public static RemoveOrderItemCommand givenRemoveOrderItemCommand() {
        return new RemoveOrderItemCommand(givenUserId(), givenOrderRestaurantId(1), givenOrderItemId(1));
    }

    public static UUID givenUserId() {
        return UUID.nameUUIDFromBytes("user-1".getBytes());
    }
}
