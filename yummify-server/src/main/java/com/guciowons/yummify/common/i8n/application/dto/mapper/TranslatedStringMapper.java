package com.guciowons.yummify.common.i8n.application.dto.mapper;

import com.guciowons.yummify.common.i8n.application.dto.TranslatedStringDTO;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.infrastructure.web.LanguageContext;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TranslatedStringMapper {
    public abstract TranslatedStringDTO mapToDTO(TranslatedString translatedString);

    public String mapToString(TranslatedString translatedString) {
        LanguageContext languageContext = LanguageContext.get();
        return translatedString.get(languageContext.language(), languageContext.defaultLanguage());
    }

    public abstract TranslatedString mapToEntity(TranslatedStringDTO dto);
}
