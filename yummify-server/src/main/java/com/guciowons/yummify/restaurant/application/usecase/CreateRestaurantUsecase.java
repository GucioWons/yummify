package com.guciowons.yummify.restaurant.application.usecase;

import com.guciowons.yummify.auth.UserFacadePort;
import com.guciowons.yummify.auth.RoleFacadePort;
import com.guciowons.yummify.common.core.application.annotation.Usecase;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.common.security.domain.Permission;
import com.guciowons.yummify.restaurant.application.model.CreateRestaurantCommand;
import com.guciowons.yummify.restaurant.application.model.RestaurantOwner;
import com.guciowons.yummify.restaurant.domain.entity.Restaurant;
import com.guciowons.yummify.restaurant.domain.port.out.RestaurantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Usecase
@RequiredArgsConstructor
public class CreateRestaurantUsecase {
    private final RestaurantRepository restaurantRepository;
    private final UserFacadePort userFacadePort;
    private final RoleFacadePort roleFacadePort;

    @Transactional
    public Restaurant create(CreateRestaurantCommand command) {
        Restaurant restaurant = Restaurant.create(command.name(), command.description(), command.defaultLanguage());

        UUID roleId = createOwnerRole(restaurant.getId());
        UUID ownerId = createOwnerUser(command.owner(), restaurant.getId(), roleId);

        restaurant.changeOwner(Restaurant.OwnerId.of(ownerId));

        restaurantRepository.save(restaurant);

        return restaurant;
    }

    private UUID createOwnerRole(Restaurant.Id restaurantId) {
        return roleFacadePort.createAndGetId(
                restaurantId.value(),
                Map.of(Language.EN.name(), "Owner"),
                Set.of(Permission.OWNER.name())
        );
    }

    private UUID createOwnerUser(RestaurantOwner owner, Restaurant.Id restaurantId, UUID roleId) {
        return userFacadePort.createUserAndGetId(
                owner.email(),
                owner.username(),
                owner.firstName(),
                owner.lastName(),
                restaurantId.value(),
                roleId,
                true
        );
    }
}
