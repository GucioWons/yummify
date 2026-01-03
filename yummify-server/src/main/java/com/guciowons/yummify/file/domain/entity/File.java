package com.guciowons.yummify.file.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.BaseEntity;
import com.guciowons.yummify.common.core.domain.entity.RestaurantScoped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "file", schema = "file")
public class File implements BaseEntity, RestaurantScoped {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID restaurantId;

    @Column(nullable = false)
    private String storageKey;

    public File(UUID restaurantId, String storageKey) {
        this.restaurantId = restaurantId;
        this.storageKey = storageKey;
    }
}
