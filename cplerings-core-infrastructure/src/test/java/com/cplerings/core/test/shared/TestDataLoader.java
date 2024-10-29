package com.cplerings.core.test.shared;

import lombok.Builder;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Builder
public final class TestDataLoader {

    private String folder;
    private ObjectMapper objectMapper;

    public <T> T loadAsObject(String fileName, Class<T> clazz) {
        try (final InputStream is = TestDataLoader.class.getClassLoader().getResourceAsStream(folder + fileName)) {
            return objectMapper.readValue(is, clazz);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public String loadAsString(String fileName) {
        try {
            final URL url = TestDataLoader.class.getClassLoader().getResource(folder + fileName);
            Objects.requireNonNull(url, "File does not exist: " + folder + fileName);
            return Files.readString(Path.of(url.toURI()), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
