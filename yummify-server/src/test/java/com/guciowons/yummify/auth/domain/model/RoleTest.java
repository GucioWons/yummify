package com.guciowons.yummify.auth.domain.model;

import com.guciowons.yummify.common.security.domain.Permission;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.givenRoleName;
import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.givenRoleRestaurantId;
import static org.assertj.core.api.Assertions.assertThat;

class RoleTest {
    @Test
    void shouldCreateRole() {
        // given
        var restaurantId = givenRoleRestaurantId(1);
        var name = givenRoleName(1);
        var permissions = Collections.singleton(Permission.OWNER);

        // when
        var result = Role.create(restaurantId, name, permissions);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getRestaurantId()).isEqualTo(restaurantId);
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getPermissions()).isEqualTo(permissions);
    }
}
