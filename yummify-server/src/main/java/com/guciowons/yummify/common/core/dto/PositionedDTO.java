package com.guciowons.yummify.common.core.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PositionedDTO implements BaseEntityDTO {
    @NotNull
    private Integer position;
}
