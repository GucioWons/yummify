package com.guciowons.yummify.dish.domain.entity;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.infrastructure.jpa.TranslatedStringConverter;
import com.guciowons.yummify.dish.domain.entity.value.DishId;
import com.guciowons.yummify.dish.domain.entity.value.DishImageId;
import com.guciowons.yummify.restaurant.RestaurantId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "dish", schema = "dish")
public class Dish {
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id", nullable = false))
    private DishId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "restaurant_id", nullable = false))
    private RestaurantId restaurantId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    @Convert(converter = TranslatedStringConverter.class)
    private TranslatedString name;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    @Convert(converter = TranslatedStringConverter.class)
    private TranslatedString description;

    @ElementCollection
    @CollectionTable(
            name = "dish_ingredient_id",
            joinColumns = @JoinColumn(name = "dish_id"),
            schema = "dish"
    )
    @Column(name = "ingredient_id")
    private List<UUID> ingredientIds;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "image_id", nullable = false))
    private DishImageId imageId;

    public static Dish of(
            RestaurantId restaurantId,
            TranslatedString name,
            TranslatedString description,
            List<UUID> ingredientIds
    ) {
        return new Dish(DishId.random(), restaurantId, name, description, ingredientIds, null);
    }

    public void update(TranslatedString name, TranslatedString description, List<UUID> ingredientIds) {
        this.name = name;
        this.description = description;
        this.ingredientIds = ingredientIds;
    }

    public void changeImage(DishImageId imageId) {
        this.imageId = imageId;
    }
}
