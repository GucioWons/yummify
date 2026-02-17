package com.guciowons.yummify.dish.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.dish.domain.entity.Dish;
import lombok.Getter;

@Getter
public class DishNotFoundException extends DomainException {
    private final Dish.Id id;

    public DishNotFoundException(Dish.Id id) {
        super("Dish not found");
        this.id = id;
    }
}
