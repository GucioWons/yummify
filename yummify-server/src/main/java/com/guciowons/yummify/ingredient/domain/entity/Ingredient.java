package com.guciowons.yummify.ingredient.domain.entity;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.ingredient.domain.entity.value.IngredientId;
import com.guciowons.yummify.restaurant.RestaurantId;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "ingredient", schema = "ingredient")
public class Ingredient {
    @EmbeddedId
    private IngredientId id;

    @Embedded
    private RestaurantId restaurantId;

    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private TranslatedString name;

    public static Ingredient of(RestaurantId restaurantId, TranslatedString name) {
        return new Ingredient(IngredientId.random(), restaurantId, name);
    }

    public void update(TranslatedString name) {
        this.name = name;
    }
}
