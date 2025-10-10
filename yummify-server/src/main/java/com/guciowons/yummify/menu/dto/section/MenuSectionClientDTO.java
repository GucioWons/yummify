package com.guciowons.yummify.menu.dto.section;

import com.guciowons.yummify.common.core.dto.PositionedDTO;
import com.guciowons.yummify.menu.dto.entry.MenuEntryDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuSectionClientDTO extends PositionedDTO {
    private String name;
    private List<MenuEntryDTO> entries;
}
