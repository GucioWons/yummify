package com.guciowons.yummify.dish.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import com.guciowons.yummify.dish.domain.exception.message.DishErrorMessage;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class DishIngredientsNotFoundException extends DishDomainException {
    private final List<UUID> ids;

    public DishIngredientsNotFoundException(List<UUID> ids) {
        super(DishErrorMessage.DISH_INGREDIENTS_NOT_FOUND_BY_IDS, ErrorProperty.of("ids", ids));
        this.ids = ids;
    }
}
