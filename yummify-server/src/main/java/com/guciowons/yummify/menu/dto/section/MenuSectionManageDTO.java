package com.guciowons.yummify.menu.dto.section;

import com.guciowons.yummify.common.core.dto.PositionedDTO;
import com.guciowons.yummify.common.core.validation.CheckPositionedList;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import com.guciowons.yummify.menu.dto.entry.MenuEntryDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuSectionManageDTO extends PositionedDTO {
    @NotNull
    private TranslatedStringDTO name;

    @Valid
    @NotNull
    @NotEmpty
    @CheckPositionedList
    private List<MenuEntryDTO> entries;
}
