package com.guciowons.yummify.dish.entity;

import com.guciowons.yummify.common.i8n.TranslatedString;
import com.guciowons.yummify.common.i8n.TranslatedStringConverter;
import com.guciowons.yummify.common.core.entity.BaseEntity;
import com.guciowons.yummify.common.core.entity.RestaurantScoped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Dish implements BaseEntity, RestaurantScoped {
    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID restaurantId;

    @Convert(converter = TranslatedStringConverter.class)
    @Column(nullable = false, columnDefinition = "jsonb")
    private TranslatedString name;

    @Column(columnDefinition = "jsonb")
    @Convert(converter = TranslatedStringConverter.class)
    private TranslatedString description;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "dishId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId")
    )
    private List<Ingredient> ingredients;
}
