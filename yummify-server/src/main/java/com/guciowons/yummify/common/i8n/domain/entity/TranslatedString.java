package com.guciowons.yummify.common.i8n.domain.entity;

import com.guciowons.yummify.common.core.domain.entity.ValueObject;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.common.i8n.domain.exception.TranslationsCannotBeEmpty;

import java.util.Map;

public record TranslatedString(Map<Language, Translation> value) implements ValueObject<Map<Language, Translation>> {
    public TranslatedString {
        if (value.isEmpty()) {
            throw new TranslationsCannotBeEmpty();
        }
        value = Map.copyOf(value);
    }

    public static TranslatedString of(Map<Language, Translation> value) {
        return new TranslatedString(value);
    }

    public Translation translateTo(Language language, Language defaultLanguage) {
        return value.getOrDefault(language, value.get(defaultLanguage));
    }
}
