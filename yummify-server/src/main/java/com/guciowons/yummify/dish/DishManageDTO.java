package com.guciowons.yummify.dish;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishManageDTO extends DishDTO{
    private TranslatedStringDTO name;
    private TranslatedStringDTO description;
}
