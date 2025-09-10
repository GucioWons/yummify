package com.guciowons.yummify.restaurant.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.i8n.Language;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO extends BaseEntityDTO {
    @NotNull
    private String name;
    @NotNull
    private Language defaultLanguage;
}
