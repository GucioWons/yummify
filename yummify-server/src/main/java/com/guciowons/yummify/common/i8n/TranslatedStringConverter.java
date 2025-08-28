package com.guciowons.yummify.common.i8n;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Map;

@Converter
public class TranslatedStringConverter implements AttributeConverter<TranslatedString, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(TranslatedString attribute) {
        try {
            return objectMapper.writeValueAsString(attribute.getTranslations());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TranslatedString convertToEntityAttribute(String dbData) {
        try {
            Map<String, String> map = objectMapper.readValue(dbData, new TypeReference<>() {});
            TranslatedString ts = new TranslatedString();
            map.forEach((key, value) -> ts.put(Language.valueOf(key), value));
            return ts;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
