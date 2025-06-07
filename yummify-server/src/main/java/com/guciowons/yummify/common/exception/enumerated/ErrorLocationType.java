package com.guciowons.yummify.common.exception.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorLocationType {
    PATH_PARAM("Path Parameter"),
    QUERY_PARAM("Query Parameter"),
    HEADER_PARAM("Header Parameter"),
    BODY("Body"),
    FILE("File");

    private final String name;
}
