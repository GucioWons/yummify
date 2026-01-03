package com.guciowons.yummify.common.i8n.application.dto;

import com.guciowons.yummify.common.i8n.domain.enumerated.Language;

import java.util.Map;

public record TranslatedStringDTO(Map<Language, String> translations) {
}
