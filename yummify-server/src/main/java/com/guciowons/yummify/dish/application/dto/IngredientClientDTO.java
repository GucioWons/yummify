package com.guciowons.yummify.dish.application.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class IngredientClientDTO implements BaseEntityDTO {
    private UUID id;
    private String name;
}
