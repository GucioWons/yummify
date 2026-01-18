package com.guciowons.yummify.table.domain.entity;

import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.table.domain.entity.value.TableId;
import com.guciowons.yummify.table.domain.entity.value.TableName;
import com.guciowons.yummify.table.domain.entity.value.TableUserId;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@jakarta.persistence.Table(name = "my_table", schema = "my_table")
public class Table {
    @EmbeddedId
    private TableId id;

    @Embedded
    private RestaurantId restaurantId;

    @Embedded
    private TableUserId userId;

    @Embedded
    private TableName name;

    public static Table of(RestaurantId restaurantId, TableName name) {
        return new Table(TableId.random(), restaurantId, null, name);
    }

    public void update(TableName name) {
        this.name = name;
    }

    public void changeUser(TableUserId userId) {
        this.userId = userId;
    }
}
