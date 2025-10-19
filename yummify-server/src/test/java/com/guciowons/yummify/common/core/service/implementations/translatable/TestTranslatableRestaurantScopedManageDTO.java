package com.guciowons.yummify.common.core.service.implementations.translatable;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestTranslatableRestaurantScopedManageDTO extends BaseEntityDTO {
    private TranslatedStringDTO test;
}
