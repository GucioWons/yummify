package com.guciowons.yummify.order.application.fixture;

import com.guciowons.yummify.order.application.command.CreateOrderCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.givenOrderRestaurantId;
import static com.guciowons.yummify.order.domain.fixture.OrderDomainFixture.givenOrderTableId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderApplicationFixture {
    public static CreateOrderCommand givenCreateOrderCommand() {
        return new CreateOrderCommand(givenOrderRestaurantId(1), givenOrderTableId(1));
    }
}
