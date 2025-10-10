package com.guciowons.yummify.dish;

import com.guciowons.yummify.dish.dto.DishClientDTO;

import java.util.UUID;

public interface PublicDishService {
    void validateDishId(UUID id);
    DishClientDTO getClientDTO(UUID id);
}
