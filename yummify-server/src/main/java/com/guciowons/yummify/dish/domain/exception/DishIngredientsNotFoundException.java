package com.guciowons.yummify.dish.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class DishIngredientsNotFoundException extends DomainException {
    private final List<UUID> ids;

    public DishIngredientsNotFoundException(List<UUID> ids) {
        super("Dish ingredients not found");
        this.ids = ids;
    }
}
