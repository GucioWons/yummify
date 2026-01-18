package com.guciowons.yummify.auth.domain.model;

import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.auth.domain.fixture.AuthDomainFixture.givenUser;
import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    @Test
    public void shouldReturnTrue_whenPasswordIsNotNull() {
        // given
        var user = givenUser(true);

        // when
        var result = user.hasPassword();

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldReturnFalse_whenPasswordIsNull() {
        // given
        var user = givenUser(false);

        // when
        var result = user.hasPassword();

        // then
        assertThat(result).isFalse();
    }
}
