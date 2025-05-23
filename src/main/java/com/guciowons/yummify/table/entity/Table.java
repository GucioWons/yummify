package com.guciowons.yummify.table.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Table {
    private UUID id;
    private String name;
    private UUID restaurantId;
}
