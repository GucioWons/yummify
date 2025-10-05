package com.guciowons.yummify.menu.dto;

import com.guciowons.yummify.menu.dto.section.MenuSectionManageDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuManageDTO extends MenuDTO {
    private List<MenuSectionManageDTO> sections;
}
