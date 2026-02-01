package com.guciowons.yummify.common.i8n.infrastructure.in.rest;

import com.guciowons.yummify.common.i8n.domain.enumerated.Language;

public record LanguageContext(Language language, Language defaultLanguage) {
    private static final ThreadLocal<LanguageContext> current = new ThreadLocal<>();

    public static void set(LanguageContext context) {
        current.set(context);
    }

    public static LanguageContext get() {
        return current.get();
    }

    public static void clear() {
        current.remove();
    }
}
