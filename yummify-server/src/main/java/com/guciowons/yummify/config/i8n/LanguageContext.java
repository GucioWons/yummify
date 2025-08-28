package com.guciowons.yummify.config.i8n;

import com.guciowons.yummify.common.i8n.Language;

public class LanguageContext {
    private static final ThreadLocal<Language> current = new ThreadLocal<>();

    public static void set(Language language) {
        current.set(language);
    }

    public static Language get() {
        return current.get();
    }

    public static void clear() {
        current.remove();
    }
}
