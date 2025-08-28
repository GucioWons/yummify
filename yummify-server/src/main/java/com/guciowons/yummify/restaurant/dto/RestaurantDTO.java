package com.guciowons.yummify.restaurant.dto;

import com.guciowons.yummify.common.i8n.Language;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RestaurantDTO<T> {
    @Null
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private T description;
    @NotNull
    private Language defaultLanguage;
}
