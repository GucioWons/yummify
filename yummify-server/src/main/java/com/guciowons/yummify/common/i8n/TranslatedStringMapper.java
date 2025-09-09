package com.guciowons.yummify.common.i8n;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TranslatedStringMapper {
    public abstract TranslatedStringDTO mapToDTO(TranslatedString translatedString);

    public String mapToString(TranslatedString translatedString) {
        return translatedString.get();
    }

    public abstract TranslatedString mapToEntity(TranslatedStringDTO dto);
}
