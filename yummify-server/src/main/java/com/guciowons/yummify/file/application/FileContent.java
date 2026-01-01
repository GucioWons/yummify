package com.guciowons.yummify.file.application;

import java.io.InputStream;

public record FileContent(String originalFilename, String contentType, InputStream inputStream, Long size) {
}
