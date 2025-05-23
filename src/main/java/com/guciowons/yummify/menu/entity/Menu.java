package com.guciowons.yummify.menu.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Menu {
    private UUID restaurantId;
    private List<MenuSection> sections;
}
