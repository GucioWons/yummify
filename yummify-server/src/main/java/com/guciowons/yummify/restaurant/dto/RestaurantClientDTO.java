package com.guciowons.yummify.restaurant.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantClientDTO extends RestaurantDTO {
    @NotNull
    private String description;
}
