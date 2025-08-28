package com.guciowons.yummify.common.i8n;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Language {
    POLISH("PL"),
    ENGLISH("EN"),
    GERMAN("DE");

    private final String code;

    public static Language fromCode(String code) {
        return Arrays.stream(values())
                .filter(language -> language.getCode().equals(code))
                .findFirst()
                .orElseThrow();
    }
}
