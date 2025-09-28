package com.guciowons.yummify.restaurant.dto;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantManageDTO extends RestaurantDTO{
    @NotNull
    private TranslatedStringDTO description;
}
