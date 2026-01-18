package com.guciowons.yummify.dish;

import org.springframework.modulith.NamedInterface;

import java.util.List;
import java.util.UUID;

@NamedInterface(name = "DishExistencePort")
public interface DishExistencePort {
    List<UUID> findMissing(List<UUID> ids, UUID restaurantId);
}
