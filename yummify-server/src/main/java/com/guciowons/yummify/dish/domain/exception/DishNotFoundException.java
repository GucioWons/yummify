package com.guciowons.yummify.dish.domain.exception;

import com.guciowons.yummify.common.exception.domain.exception.DomainException;
import com.guciowons.yummify.dish.domain.entity.value.DishId;
import lombok.Getter;

@Getter
public class DishNotFoundException extends DomainException {
    private final DishId id;

    public DishNotFoundException(DishId id) {
        super("Dish not found");
        this.id = id;
    }
}
