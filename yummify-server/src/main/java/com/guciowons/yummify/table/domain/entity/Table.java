package com.guciowons.yummify.table.domain.entity;

import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.table.domain.entity.value.TableId;
import com.guciowons.yummify.table.domain.entity.value.TableName;
import com.guciowons.yummify.table.domain.entity.value.TableUserId;
import jakarta.persistence.*;
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
    @AttributeOverride(name = "value", column = @Column(name = "id", nullable = false))
    private TableId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "restaurant_id", nullable = false))
    private RestaurantId restaurantId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_id", nullable = false))
    private TableUserId userId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name", nullable = false))
    private TableName name;

    public static Table of(RestaurantId restaurantId, TableName name) {
        return new Table(TableId.random(), restaurantId, null, name);
    }

    public void updateDetails(TableName name) {
        this.name = name;
    }

    public void changeUser(TableUserId userId) {
        this.userId = userId;
    }
}
