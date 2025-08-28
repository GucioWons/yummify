package com.guciowons.yummify.restaurant.entity;

import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.i8n.TranslatedString;
import com.guciowons.yummify.common.i8n.TranslatedStringConverter;
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
    @Convert(converter = TranslatedStringConverter.class)
    private TranslatedString description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language defaultLanguage;
}
