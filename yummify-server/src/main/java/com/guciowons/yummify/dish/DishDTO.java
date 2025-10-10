package com.guciowons.yummify.dish;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DishDTO extends BaseEntityDTO {
    @Valid
    @NotNull
    private List<IngredientClientDTO> ingredients;
}
