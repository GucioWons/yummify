package com.guciowons.yummify.menu.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class MenuSection {
    private UUID id;
    private String name;
    private List<UUID> dishIds;
}
