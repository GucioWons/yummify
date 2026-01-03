package com.guciowons.yummify.common.i8n.domain.entity;

import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class TranslatedString {
    private final HashMap<Language, String> translations = new HashMap<>();

    public String get(Language language, Language defaultLanguage) {
        return translations.getOrDefault(language, translations.get(defaultLanguage));
    }

    public void put(Language lang, String text) {
        translations.put(lang, text);
    }
}
