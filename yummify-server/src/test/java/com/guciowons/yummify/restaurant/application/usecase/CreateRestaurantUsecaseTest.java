package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.auth.AuthFacadePort;
import com.guciowons.yummify.auth.RoleFacadePort;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.common.security.domain.Permission;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.domain.port.out.RestaurantRepository;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static com.guciowons.yummify.restaurant.application.fixture.RestaurantApplicationFixture.givenCreateRestaurantCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CreateRestaurantUsecaseTest {
    private final RestaurantRepository restaurantRepository = mock(RestaurantRepository.class);
    private final AuthFacadePort authFacadePort = mock(AuthFacadePort.class);
    private final RoleFacadePort roleFacadePort = mock(RoleFacadePort.class);

    private final CreateRestaurantUsecase underTest = new CreateRestaurantUsecase(restaurantRepository, authFacadePort, roleFacadePort);

    @Test
    void shouldCreateRestaurant() {
        // given
        var command = givenCreateRestaurantCommand();
        var ownerRoleId = UUID.nameUUIDFromBytes("role".getBytes());
        var ownerRoleName = Map.of(Language.EN.name(), "Owner");
        var ownerRolePermissions = Set.of(Permission.OWNER.name());
        var ownerId = UUID.nameUUIDFromBytes("user".getBytes());

        when(roleFacadePort.createAndGetId(any(), eq(ownerRoleName), eq(ownerRolePermissions))).thenReturn(ownerRoleId);
        when(authFacadePort.createUser(
                eq(command.owner().email()),
                eq(command.owner().username()),
                eq(command.owner().firstName()),
                eq(command.owner().lastName()),
                any(),
                eq(ownerRoleId),
                eq(true))
        ).thenReturn(ownerId);

        // when
        var result = underTest.create(command);

        // then
        verify(roleFacadePort).createAndGetId(any(), eq(ownerRoleName), eq(ownerRolePermissions));
        verify(authFacadePort).createUser(
                command.owner().email(),
                command.owner().username(),
                command.owner().firstName(),
                command.owner().lastName(),
                result.getId().value(),
                ownerRoleId,
                true
        );
        verify(restaurantRepository).save(result);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getOwnerId()).isEqualTo(Restaurant.OwnerId.of(ownerId));
        assertThat(result.getName()).isEqualTo(command.name());
        assertThat(result.getDescription()).isEqualTo(command.description());
        assertThat(result.getDefaultLanguage()).isEqualTo(command.defaultLanguage());
    }
}
