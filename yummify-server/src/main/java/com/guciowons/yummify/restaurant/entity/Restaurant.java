package com.guciowons.yummify.restaurant.entity;

import com.guciowons.yummify.common.core.entity.BaseEntity;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.i8n.TranslatedString;
import com.guciowons.yummify.common.i8n.TranslatedStringConverter;
import com.guciowons.yummify.common.core.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "restaurant", schema = "restaurant")
public class Restaurant implements BaseEntity {
    @Id
    private UUID id;

    @Column
    private UUID ownerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "jsonb")
    @Convert(converter = TranslatedStringConverter.class)
    private TranslatedString description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language defaultLanguage;
}
