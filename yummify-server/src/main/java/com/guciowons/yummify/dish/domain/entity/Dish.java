package com.guciowons.yummify.dish.domain.entity;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.dish.domain.entity.value.DishId;
import com.guciowons.yummify.dish.domain.entity.value.DishImageId;
import com.guciowons.yummify.dish.domain.entity.value.DishIngredientIds;
import com.guciowons.yummify.restaurant.RestaurantId;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "dish", schema = "dish")
public class Dish {
    @EmbeddedId
    private DishId id;

    @Embedded
    private RestaurantId restaurantId;

    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private TranslatedString name;

    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private TranslatedString description;

    @Embedded
    private DishIngredientIds ingredientIds;

    @Embedded
    private DishImageId imageId;

    public static Dish of(
            RestaurantId restaurantId,
            TranslatedString name,
            TranslatedString description,
            DishIngredientIds ingredientIds
    ) {
        return new Dish(DishId.random(), restaurantId, name, description, ingredientIds, null);
    }

    public void update(TranslatedString name, TranslatedString description, DishIngredientIds ingredientIds) {
        this.name = name;
        this.description = description;
        this.ingredientIds = ingredientIds;
    }

    public void changeImage(DishImageId imageId) {
        this.imageId = imageId;
    }
}
