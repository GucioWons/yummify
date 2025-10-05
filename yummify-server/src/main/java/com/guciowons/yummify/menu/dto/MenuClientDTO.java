package com.guciowons.yummify.menu.dto;

import com.guciowons.yummify.menu.dto.section.MenuSectionClientDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuClientDTO extends MenuDTO {
    private List<MenuSectionClientDTO> sections;
}
