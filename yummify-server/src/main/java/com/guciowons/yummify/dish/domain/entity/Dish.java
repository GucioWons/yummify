package com.guciowons.yummify.dish.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.BaseEntity;
import com.guciowons.yummify.common.core.domain.entity.RestaurantScoped;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "dish", schema = "dish")
public class Dish implements BaseEntity, RestaurantScoped {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID restaurantId;

    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private TranslatedString name;

    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private TranslatedString description;

    @ElementCollection
    @CollectionTable(
            name = "dish_ingredient_id",
            joinColumns = @JoinColumn(name = "dish_id"),
            schema = "dish"
    )
    @Column(name = "ingredient_id")
    private List<UUID> ingredientIds = new ArrayList<>();

    private UUID imageId;

    public void setIngredientIds(List<UUID> ingredientIds) {
        this.ingredientIds.clear();
        this.ingredientIds.addAll(ingredientIds);
    }
}
