package com.guciowons.yummify.common.i8n.infrastructure.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guciowons.yummify.common.i8n.domain.entity.TranslatedString;
import com.guciowons.yummify.common.i8n.domain.entity.Translation;
import com.guciowons.yummify.common.i8n.domain.enumerated.Language;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Map;
import java.util.stream.Collectors;

@Converter
public class TranslatedStringConverter implements AttributeConverter<TranslatedString, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(TranslatedString attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            Map<Language, String> flatMap = attribute.value().entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().value()));

            return objectMapper.writeValueAsString(flatMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert TranslatedString to JSON", e);
        }
    }

    @Override
    public TranslatedString convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            Map<Language, String> flatMap = objectMapper.readValue(
                    dbData,
                    objectMapper.getTypeFactory().constructMapType(Map.class, Language.class, String.class)
            );

            if (flatMap == null || flatMap.isEmpty()) {
                return null;
            }

            Map<Language, Translation> map = flatMap.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> new Translation(e.getValue())));

            return new TranslatedString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON to TranslatedString", e);
        }
    }
}
