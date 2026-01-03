package com.guciowons.yummify.common;

import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.application.dto.TranslatedStringDTO;

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
