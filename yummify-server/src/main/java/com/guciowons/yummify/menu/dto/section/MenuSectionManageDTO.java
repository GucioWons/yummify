package com.guciowons.yummify.menu.dto.section;

import com.guciowons.yummify.common.i8n.TranslatedStringDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuSectionManageDTO extends MenuSectionDTO{
    private TranslatedStringDTO name;
}
