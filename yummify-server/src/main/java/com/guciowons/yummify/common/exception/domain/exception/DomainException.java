package com.guciowons.yummify.common.exception.domain.exception;

import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import com.guciowons.yummify.common.exception.domain.model.ErrorProperty;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public abstract class DomainException extends RuntimeException {
    private final ErrorMessage errorMessage;
    private final List<ErrorProperty> properties;

    public DomainException(ErrorMessage errorMessage) {
        super(buildMessage(errorMessage));
        this.errorMessage = errorMessage;
        this.properties = null;
    }

    public DomainException(ErrorMessage errorMessage, ErrorProperty... properties) {
        super(buildMessage(errorMessage, properties));
        this.errorMessage = errorMessage;
        this.properties = List.of(properties);
    }

    public Map<String, Object> getPropertiesAsMap() {
        return properties.stream()
                .map(property -> Map.entry(property.key(), property.value()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static String buildMessage(ErrorMessage errorMessage, ErrorProperty... properties) {
        String message = errorMessage.getMessage();

        if (properties == null) {
            return message;
        }

        for (ErrorProperty entry : properties) {
            message = fillProperty(message, entry.key(), entry.value());
        }
        return message;
    }

    private static String fillProperty(String message, String key, Object value) {
        String placeholder = "{{%s}}".formatted(key);
        return message.replace(placeholder, value.toString());
    }
}
