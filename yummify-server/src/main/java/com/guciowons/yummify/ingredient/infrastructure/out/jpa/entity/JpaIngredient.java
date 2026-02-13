package com.guciowons.yummify.ingredient.infrastructure.out.jpa.entity;

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
@Table(name = "ingredient", schema = "ingredient")
public class JpaIngredient {
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID restaurantId;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb", nullable = false)
    private Map<String, String> name;
}
