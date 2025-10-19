package com.guciowons.yummify.restaurant.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.i8n.Language;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantClientDTO extends BaseEntityDTO {
    private String name;
    private Language defaultLanguage;
    private String description;
}
