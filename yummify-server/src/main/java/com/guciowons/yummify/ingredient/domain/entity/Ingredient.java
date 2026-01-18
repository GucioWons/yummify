package com.guciowons.yummify.ingredient.domain.entity;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.infrastructure.jpa.TranslatedStringConverter;
import com.guciowons.yummify.ingredient.domain.entity.value.IngredientId;
import com.guciowons.yummify.restaurant.RestaurantId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "ingredient", schema = "ingredient")
public class Ingredient {
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id", nullable = false))
    private IngredientId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "restaurant_id", nullable = false))
    private RestaurantId restaurantId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    @Convert(converter = TranslatedStringConverter.class)
    private TranslatedString name;

    public static Ingredient of(RestaurantId restaurantId, TranslatedString name) {
        return new Ingredient(IngredientId.random(), restaurantId, name);
    }

    public void update(TranslatedString name) {
        this.name = name;
    }
}
