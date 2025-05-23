package com.guciowons.yummify.restaurant.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Restaurant {
    private UUID id;
    private String name;
}
