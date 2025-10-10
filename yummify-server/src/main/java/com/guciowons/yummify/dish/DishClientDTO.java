package com.guciowons.yummify.dish;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishClientDTO extends DishDTO {
    private String name;

    private String description;
}
