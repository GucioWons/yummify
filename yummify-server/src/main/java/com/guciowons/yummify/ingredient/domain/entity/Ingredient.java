package com.guciowons.yummify.ingredient.domain.entity;

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

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "ingredient", schema = "ingredient")
public class Ingredient implements BaseEntity, RestaurantScoped {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID restaurantId;

    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private TranslatedString name;
}
