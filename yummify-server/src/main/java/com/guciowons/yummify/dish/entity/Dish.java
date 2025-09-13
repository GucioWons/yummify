package com.guciowons.yummify.dish.entity;

import com.guciowons.yummify.common.core.entity.BaseEntity;
import com.guciowons.yummify.common.core.entity.RestaurantScoped;
import com.guciowons.yummify.common.i8n.TranslatedString;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

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

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "dishId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId"),
            schema = "dish"
    )
    private List<Ingredient> ingredients;
}
