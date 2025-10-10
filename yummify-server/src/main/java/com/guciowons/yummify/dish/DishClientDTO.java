package com.guciowons.yummify.dish;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishClientDTO extends DishDTO {
    @NotNull
    private String name;

    @NotNull
    private String description;
}
