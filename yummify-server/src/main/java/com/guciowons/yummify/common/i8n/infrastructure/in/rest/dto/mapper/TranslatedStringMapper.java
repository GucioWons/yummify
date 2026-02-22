package com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.TranslatedStringDto;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.entity.Translation;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.common.i8n.infrastructure.in.rest.LanguageContext;
import org.mapstruct.Mapper;

import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class TranslatedStringMapper {
    public TranslatedStringDto toDto(TranslatedString translatedString) {
        if (translatedString == null) {
            return null;
        }

        Map<String, String> translations = translatedString.value().entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().name(),
                        e -> e.getValue().value())
                );

        return new TranslatedStringDto(translations);
    }

    public TranslatedString map(Map<String, String> translations) {
        Map<Language, Translation> translations2 = translations.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> Language.valueOf(e.getKey()),
                        e -> Translation.of(e.getValue()))
                );

        return TranslatedString.of(translations2);
    }

    public String toString(TranslatedString translatedString) {
        LanguageContext languageContext = LanguageContext.get();
        return translatedString.translateTo(languageContext.language(), languageContext.defaultLanguage()).value();
    }
}
