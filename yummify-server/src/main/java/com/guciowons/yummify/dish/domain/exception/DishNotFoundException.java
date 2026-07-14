package com.guciowons.yummify.dish.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.dish.domain.entity.Dish;
import com.guciowons.yummify.dish.domain.exception.message.DishErrorMessage;
import lombok.Getter;

@Getter
public class DishNotFoundException extends DishDomainException {
    private final Dish.Id id;

    public DishNotFoundException(Dish.Id id) {
        super(DishErrorMessage.DISH_NOT_FOUND_BY_ID, ErrorProperty.of("id", id.value()));
        this.id = id;
    }
}
