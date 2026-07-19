package com.guciowons.yummify.dish;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;

public record DishContract(TranslatedString name) {
    public static DishContract of(TranslatedString name) {
        return new DishContract(name);
    }
}
