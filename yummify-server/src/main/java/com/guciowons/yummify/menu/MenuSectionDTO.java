package com.guciowons.yummify.menu;

import com.guciowons.yummify.dish.DishSelectDTO;

import java.util.List;

public record MenuSectionDTO(String name, List<DishSelectDTO> dishes) {
}
