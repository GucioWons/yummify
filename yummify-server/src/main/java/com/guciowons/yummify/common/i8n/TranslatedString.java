package com.guciowons.yummify.common.i8n;

import com.guciowons.yummify.common.request.RequestContext;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class TranslatedString {
    private final HashMap<Language, String> translations = new HashMap<>();

    public String get() {
        Language language = RequestContext.get().getLanguage();
        Language defaultLanguage = RequestContext.get().getDefaultLanguage();

        return translations.getOrDefault(language, translations.get(defaultLanguage));
    }

    public void put(Language lang, String text) {
        translations.put(lang, text);
    }
}
