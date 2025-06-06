package com.guciowons.yummify.restaurant;

import com.guciowons.yummify.auth.UserRequestDTO;

public record RestaurantCreateDTO(String name, UserRequestDTO owner) {
}
