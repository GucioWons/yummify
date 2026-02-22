package com.guciowons.yummify.restaurant.infrastructure.out.jpa.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "restaurant", schema = "restaurant")
public class JpaRestaurant {
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID ownerId;

    @Column(nullable = false)
    private String name;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, String> description;

    @Column(nullable = false)
    private String defaultLanguage;
}
