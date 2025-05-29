package com.guciowons.yummify.menu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Menu {
    @Id
    private UUID restaurantId;

    @OneToMany
    private List<MenuSection> sections;
}
