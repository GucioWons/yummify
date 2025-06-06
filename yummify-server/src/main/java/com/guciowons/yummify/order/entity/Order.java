package com.guciowons.yummify.order.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Order {
    private UUID id;
    private UUID restaurantId;
    private UUID tableId;
}
