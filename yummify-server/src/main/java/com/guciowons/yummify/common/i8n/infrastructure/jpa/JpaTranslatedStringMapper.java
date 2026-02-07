package com.guciowons.yummify.common.i8n.infrastructure.jpa;

import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.entity.Translation;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import org.mapstruct.Mapper;

import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public class JpaTranslatedStringMapper {
    public TranslatedString toDomain(Map<String, String> jpaTranslatedString) {
        Map<Language, Translation> translationsMap = jpaTranslatedString.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> Language.valueOf(e.getKey()),
                        e -> Translation.of(e.getValue())
                ));

        return TranslatedString.of(translationsMap);
    }

    public Map<String, String> toJpa(TranslatedString translatedString) {
        return translatedString.value()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().name(),
                        e -> e.getValue().value()
                ));
    }
}
