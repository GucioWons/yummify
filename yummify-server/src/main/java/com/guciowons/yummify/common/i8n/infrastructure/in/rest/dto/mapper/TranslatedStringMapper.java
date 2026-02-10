package com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.mapper;

import com.guciowons.yummify.common.i8n.infrastructure.in.rest.dto.TranslatedStringDTO;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.entity.Translation;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.common.i8n.infrastructure.in.rest.LanguageContext;
import org.mapstruct.Mapper;

import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class TranslatedStringMapper {
    public TranslatedStringDTO toDto(TranslatedString translatedString) {
        if (translatedString == null) {
            return null;
        }

        Map<String, String> translations = translatedString.value().entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().name(),
                        e -> e.getValue().value())
                );

        return new TranslatedStringDTO(translations);
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

    public TranslatedString toEntity(TranslatedStringDTO dto) {
        Map<Language, Translation> translations = dto.translations().entrySet().stream()
                .collect(Collectors.toMap(
                        e -> Language.valueOf(e.getKey()),
                        e -> Translation.of(e.getValue()))
                );

        return TranslatedString.of(translations);
    }
}
