package com.guciowons.yummify.restaurant.domain.entity;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.restaurant.domain.entity.value.RestaurantName;
import com.guciowons.yummify.restaurant.domain.entity.value.RestaurantOwnerId;
import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "restaurant", schema = "restaurant")
public class Restaurant {
    @EmbeddedId
    private RestaurantId id;

    @Embedded
    private RestaurantOwnerId ownerId;

    @Embedded
    private RestaurantName name;

    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private TranslatedString description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Language defaultLanguage;

    public static Restaurant of(RestaurantName name, TranslatedString description, Language defaultLanguage) {
        return new Restaurant(RestaurantId.random(), null, name, description, defaultLanguage);
    }

    public void changeOwner(RestaurantOwnerId newOwnerId) {
        this.ownerId = newOwnerId;
    }

    public void updateDetails(RestaurantName name, TranslatedString description, Language defaultLanguage) {
        this.name = name;
        this.description = description;
        this.defaultLanguage = defaultLanguage;
    }
}
