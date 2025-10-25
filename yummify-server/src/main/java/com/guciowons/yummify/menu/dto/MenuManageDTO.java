package com.guciowons.yummify.menu.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.common.core.validation.CheckPositionedList;
import com.guciowons.yummify.menu.dto.section.MenuSectionManageDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuManageDTO extends BaseEntityDTO {
    @Valid
    @NotNull
    @NotEmpty
    @CheckPositionedList
    private List<MenuSectionManageDTO> sections;

    private boolean isActive;
}
