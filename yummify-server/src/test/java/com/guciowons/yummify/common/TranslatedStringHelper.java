package com.guciowons.yummify.common;

import com.guciowons.yummify.common.i8n.Language;
import com.guciowons.yummify.common.i8n.TranslatedString;
import com.guciowons.yummify.common.i8n.TranslatedStringDTO;

import java.util.Map;

public class TranslatedStringHelper {
    public static TranslatedString buildEntity(String text, Language language) {
        TranslatedString translatedString = new TranslatedString();
        translatedString.put(language, text);
        return translatedString;
    }

    public static TranslatedStringDTO buildDTO(String text, Language language) {
        return new TranslatedStringDTO(Map.of(language, text));
    }
}
