package com.guciowons.yummify.menu.dto.entry;

import com.guciowons.yummify.common.core.dto.PositionedDTO;
import com.guciowons.yummify.common.core.validation.CheckEntityId;
import com.guciowons.yummify.dish.application.dish.dto.DishClientDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MenuEntryDTO extends PositionedDTO {
    @Valid
    @NotNull
    @CheckEntityId
    private DishClientDTO dish;

    @Valid
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 4, fraction = 2)
    private BigDecimal price;
}
