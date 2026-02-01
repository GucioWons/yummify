package com.guciowons.yummify.menu.domain.entity.value;

import com.guciowons.yummify.common.core.domain.entity.IdValueObject;

import java.util.UUID;

public record MenuId(UUID value) implements IdValueObject {
    public static MenuId of(UUID value) {
        return new MenuId(value);
    }
}
