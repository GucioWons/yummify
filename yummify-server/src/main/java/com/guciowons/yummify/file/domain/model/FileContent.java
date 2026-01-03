package com.guciowons.yummify.file.domain.model;

import java.io.InputStream;

public record FileContent(String originalFilename, String contentType, InputStream inputStream, Long size) {
}
