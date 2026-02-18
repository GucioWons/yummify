package com.guciowons.yummify.file.application.model;

import com.guciowons.yummify.file.domain.model.Filename;

import java.io.InputStream;

public record FileContent(Filename filename, String contentType, InputStream inputStream, Long size) {
}
