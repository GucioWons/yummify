package com.guciowons.yummify.order.application.fixture;

import com.guciowons.yummify.order.application.command.AddOrderItemCommand;
import com.guciowons.yummify.order.application.command.CreateOrderCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderApplicationFixture {
    public static CreateOrderCommand givenCreateOrderCommand() {
        return new CreateOrderCommand(givenOrderRestaurantId(1), givenOrderTableId(1));
    }

    public static AddOrderItemCommand givenAddOrderItemCommand() {
        return new AddOrderItemCommand(givenOrderId(1), givenOrderRestaurantId(1), givenOrderItemDishId(1), 1);
    }
}
