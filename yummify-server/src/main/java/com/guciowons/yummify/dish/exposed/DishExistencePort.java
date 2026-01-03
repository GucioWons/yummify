package com.guciowons.yummify.dish.exposed;

import org.springframework.modulith.NamedInterface;

import java.util.List;
import java.util.UUID;

@NamedInterface(name = "DishExistencePort")
public interface DishExistencePort {
    void assertAllExist(List<UUID> ids, UUID restaurantId);
}
