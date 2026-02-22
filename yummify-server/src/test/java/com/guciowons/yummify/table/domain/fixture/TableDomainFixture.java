package com.guciowons.yummify.table.domain.fixture;

import com.guciowons.yummify.table.domain.entity.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TableDomainFixture {
    public static Table givenTable(int seed) {
        return new Table(
                givenTableId(seed),
                givenTableRestaurantId(seed),
                givenTableUserId(seed),
                givenTableName(seed)
        );
    }

    public static Table.Id givenTableId(int seed) {
        return Table.Id.of(UUID.nameUUIDFromBytes("table-%s".formatted(seed).getBytes()));
    }

    public static Table.RestaurantId givenTableRestaurantId(int seed) {
        return Table.RestaurantId.of(UUID.nameUUIDFromBytes("restaurant-%s".formatted(seed).getBytes()));
    }

    public static Table.Name givenTableName(int seed) {
        return Table.Name.of("table-%s".formatted(seed));
    }

    public static Table.UserId givenTableUserId(int seed) {
        return Table.UserId.of(UUID.nameUUIDFromBytes("table-user-%s".formatted(seed).getBytes()));
    }
}
