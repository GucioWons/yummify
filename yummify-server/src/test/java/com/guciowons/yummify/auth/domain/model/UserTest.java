package com.guciowons.yummify.auth.domain.model;

import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {
    @Test
    void shouldCreateUser() {
        // given
        var restaurantId = givenUserRestaurantId();
        var email = givenUserEmail();
        var username = givenUserUsername();
        var personalData = givenUserPersonalData();
        var password = givenPassword();

        // when
        var result = User.create(restaurantId, email, username, personalData, password);

        // then
        assertThat(result.getId()).isNull();
        assertThat(result.getRestaurantId()).isEqualTo(restaurantId);
        assertThat(result.getEmail()).isEqualTo(email);
        assertThat(result.getUsername()).isEqualTo(username);
        assertThat(result.getPersonalData()).isEqualTo(personalData);
        assertThat(result.getPassword()).isEqualTo(password);
    }

    @Test
    void shouldAssignId() {
        // given
        var user = givenUser(true);
        var id = givenUserExternalId(1);

        // when
        user.assignId(id);

        // then
        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    void shouldNotAssignId_whenItsAlreadyAssigned() {
        // given
        var user = givenUser(true);
        var id = givenUserExternalId(1);
        user.assignId(id);
        var newId = givenUserExternalId(2);

        // when
        assertThatThrownBy(() -> user.assignId(newId)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldReturnTrue_whenPasswordIsNotNull() {
        // given
        var user = givenUser(true);

        // when
        var result = user.hasPassword();

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFalse_whenPasswordIsNull() {
        // given
        var user = givenUser(false);

        // when
        var result = user.hasPassword();

        // then
        assertThat(result).isFalse();
    }
}
