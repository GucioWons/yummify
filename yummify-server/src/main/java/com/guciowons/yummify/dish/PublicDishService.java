package com.guciowons.yummify.dish;

import java.util.UUID;

public interface PublicDishService {
    void validateDishId(UUID id);
    DishClientDTO getClientDTO(UUID id);
}
