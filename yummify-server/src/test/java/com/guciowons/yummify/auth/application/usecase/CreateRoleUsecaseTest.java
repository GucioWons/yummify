package com.guciowons.yummify.auth.application.usecase;

import com.guciowons.yummify.auth.domain.model.Role;
import com.guciowons.yummify.auth.domain.port.out.RoleRepository;
import org.junit.jupiter.api.Test;

import static com.guciowons.yummify.auth.application.fixture.AuthApplicationFixture.givenCreateRoleCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateRoleUsecaseTest {
    private final RoleRepository roleRepository = mock(RoleRepository.class);

    private final CreateRoleUsecase underTest = new CreateRoleUsecase(roleRepository);

    @Test
    void shouldCreateRole() {
        // given
        var command = givenCreateRoleCommand();

        // when
        var result = underTest.create(command);

        // then
        verify(roleRepository).save(any(Role.class));

        assertThat(result.getId()).isNotNull();
        assertThat(result.getRestaurantId()).isEqualTo(command.restaurantId());
        assertThat(result.getName()).isEqualTo(command.name());
        assertThat(result.getPermissions()).isEqualTo(command.permissions());
    }
}
