package com.guciowons.yummify.common.exception.application.dto;

import com.guciowons.yummify.common.exception.domain.model.DomainError;
import com.guciowons.yummify.common.exception.domain.model.ErrorMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Map;

@Mapper(componentModel = "spring")
public interface ApiErrorMapper {
    @Mapping(
            target = "errorMessageString",
            expression = "java(mapErrorMessageToString(error.errorMessage(), error.properties()))"
    )
    ApiErrorDTO mapToDTO(DomainError error);

    default String mapErrorMessageToString(ErrorMessage errorMessage, Map<String, Object> properties) {
        if (properties.isEmpty()) {
            return errorMessage.getMessage();
        }
        String result = errorMessage.getMessage();

        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String placeholder = "{{" + entry.getKey() + "}}";
            result = result.replace(placeholder, String.valueOf(entry.getValue()));
        }

        return result;
    }
}
