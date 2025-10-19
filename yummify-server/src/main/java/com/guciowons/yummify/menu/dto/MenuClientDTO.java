package com.guciowons.yummify.menu.dto;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.menu.dto.section.MenuSectionClientDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuClientDTO extends BaseEntityDTO {
    private List<MenuSectionClientDTO> sections;
}
