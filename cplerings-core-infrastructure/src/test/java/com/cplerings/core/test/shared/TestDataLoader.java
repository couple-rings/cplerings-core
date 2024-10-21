package com.cplerings.core.test.shared;

import lombok.Builder;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

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
}
