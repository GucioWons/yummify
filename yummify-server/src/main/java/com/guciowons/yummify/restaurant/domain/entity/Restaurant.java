package com.guciowons.yummify.restaurant.domain.entity;

import com.guciowons.yummify.common.core.entity.BaseEntity;
import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.i8n.TranslatedString;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "restaurant", schema = "restaurant")
public class Restaurant implements BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private UUID ownerId;

    @Column(nullable = false)
    private String name;

    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private TranslatedString description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language defaultLanguage;
}
