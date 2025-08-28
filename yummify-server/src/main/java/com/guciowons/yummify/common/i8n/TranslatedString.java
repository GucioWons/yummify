package com.guciowons.yummify.common.i8n;

import java.util.HashMap;
import java.util.Map;

public class TranslatedString {
    private final HashMap<Language, String> translations = new HashMap<>();

    public String getOrDefault(Language lang, Language fallback) {
        return translations.getOrDefault(lang, translations.get(fallback));
    }

    public void put(Language lang, String text) {
        translations.put(lang, text);
    }

    public Map<Language, String> getAll() {
        return translations;
    }
}
