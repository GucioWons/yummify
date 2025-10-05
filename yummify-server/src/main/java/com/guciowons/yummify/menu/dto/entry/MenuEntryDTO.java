package com.guciowons.yummify.menu.dto.entry;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.dish.DishClientDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MenuEntryDTO extends BaseEntityDTO {
    private DishClientDTO dish;
    private Integer position;
    private BigDecimal price;
}
