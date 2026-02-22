package com.guciowons.yummify.table.domain.fixture;

import com.guciowons.yummify.restaurant.RestaurantId;
import com.guciowons.yummify.table.domain.entity.Table;
import com.guciowons.yummify.table.domain.entity.value.TableId;
import com.guciowons.yummify.table.domain.entity.value.TableName;
import com.guciowons.yummify.table.domain.entity.value.TableUserId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TableDomainFixture {
    public static Table givenTable(int seed) {
        var table = Table.of(givenRestaurantId(seed), givenTableName(seed));
        table.changeUser(givenTableUserId(seed));
        return table;
    }

    public static RestaurantId givenRestaurantId(int seed) {
        return RestaurantId.of(UUID.nameUUIDFromBytes("restaurant-%s".formatted(seed).getBytes()));
    }

    public static TableId givenTableId(int seed) {
        return TableId.of(UUID.nameUUIDFromBytes("table-%s".formatted(seed).getBytes()));
    }

    public static TableName givenTableName(int seed) {
        return TableName.of("table-%s".formatted(seed));
    }

    public static TableUserId givenTableUserId(int seed) {
        return TableUserId.of(UUID.nameUUIDFromBytes("table-user-%s".formatted(seed).getBytes()));
    }
}
