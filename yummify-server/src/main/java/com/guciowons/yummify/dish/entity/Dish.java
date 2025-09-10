package com.guciowons.yummify.dish.entity;

import com.guciowons.yummify.common.i8n.TranslatedString;
import com.guciowons.yummify.common.i8n.TranslatedStringConverter;
import com.guciowons.yummify.common.temp.RestaurantScoped;
import com.guciowons.yummify.common.temp.TempEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Dish implements TempEntity, RestaurantScoped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private UUID restaurantId;

    @Convert(converter = TranslatedStringConverter.class)
    @Column(nullable = false)
    private TranslatedString name;

    @Convert(converter = TranslatedStringConverter.class)
    @Column
    private TranslatedString description;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "dishId"),
            inverseJoinColumns = @JoinColumn(name = "ingredientId")
    )
    private List<Ingredient> ingredients;
}
