package com.guciowons.yummify.dish;

import org.springframework.modulith.NamedInterface;

import java.util.List;
import java.util.UUID;

@NamedInterface(name = "PublicDishFacadePort")
public interface PublicDishFacadePort {
    DishContract get(UUID id, UUID restaurantId);

    List<UUID> findMissing(List<UUID> ids, UUID restaurantId);
}
