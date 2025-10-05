package com.guciowons.yummify.menu.dto.section;

import com.guciowons.yummify.common.core.dto.BaseEntityDTO;
import com.guciowons.yummify.menu.dto.entry.MenuEntryDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuSectionDTO extends BaseEntityDTO {
    private Integer position;
    private List<MenuEntryDTO> entries;
}
