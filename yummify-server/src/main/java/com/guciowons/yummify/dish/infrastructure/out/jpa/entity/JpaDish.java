package com.guciowons.yummify.dish.infrastructure.out.jpa.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "dish", schema = "dish")
public class JpaDish {
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID restaurantId;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb", nullable = false)
    private Map<String, String> name;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, String> description;

    @ElementCollection
    @CollectionTable(
            name = "dish_ingredient_id",
            joinColumns = @JoinColumn(name = "dish_id"),
            schema = "dish"
    )
    @Column(name = "ingredient_id")
    private List<UUID> ingredientIds;

    @Column
    private UUID imageId;
}
