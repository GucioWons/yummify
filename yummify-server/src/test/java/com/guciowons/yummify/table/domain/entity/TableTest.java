package com.guciowons.yummify.table.domain.entity;

import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.table.domain.fixture.TableDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class TableTest {
    @Test
    void shouldCreateTableWithRandomIdAndNoUser() {
        // given
        var restaurantId = givenRestaurantId(1);
        var name = givenTableName(1);

        // when
        var result = Table.of(restaurantId, name);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getUserId()).isNull();
        assertThat(result.getRestaurantId()).isEqualTo(restaurantId);
        assertThat(result.getName()).isEqualTo(name);
    }

    @Test
    void shouldChangeUser() {
        // given
        var table = givenTable(1);
        var newUserId = givenTableUserId(2);

        // when
        table.changeUser(newUserId);

        // then
        assertThat(table.getUserId()).isEqualTo(newUserId);
    }

    @Test
    void shouldUpdateDetails() {
        // given
        var table = givenTable(1);
        var newName = givenTableName(2);

        // when
        table.updateDetails(newName);

        // then
        assertThat(table.getName()).isEqualTo(newName);
    }
}
