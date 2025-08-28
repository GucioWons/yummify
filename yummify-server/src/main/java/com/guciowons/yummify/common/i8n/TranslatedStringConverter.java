package com.guciowons.yummify.common.i8n;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Map;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class TranslatedStringConverter implements AttributeConverter<TranslatedString, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(TranslatedString attribute) {
        Map<String, String> map = attribute.getAll().entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey().getCode(),
                        Map.Entry::getValue)
                );
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TranslatedString convertToEntityAttribute(String dbData) {
        try {
            Map<String, String> map = objectMapper.readValue(dbData, new TypeReference<>() {});
            TranslatedString ts = new TranslatedString();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                ts.put(Language.fromCode(entry.getKey()), entry.getValue());
            }
            return ts;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
