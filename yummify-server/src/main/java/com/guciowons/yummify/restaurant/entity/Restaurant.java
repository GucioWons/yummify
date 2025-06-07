package com.guciowons.yummify.restaurant.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "restaurant", schema = "restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column
    private UUID ownerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
}
