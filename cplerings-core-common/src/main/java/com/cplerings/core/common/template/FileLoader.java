package com.cplerings.core.common.template;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileLoader {

    private static final Logger LOGGER = Logger.getLogger(FileLoader.class.getName());

    public static String loadTemplate(String templateFilePath) {
        final ClassLoader classLoader = FileLoader.class.getClassLoader();
        try {
            final URL templateURL = classLoader.getResource(templateFilePath);
            if (templateURL == null) {
                LOGGER.warning("Template file not found: " + templateFilePath);
                return StringUtils.EMPTY;
            }
            final Path templatePath = Path.of(templateURL.toURI());
            return Files.readString(templatePath, StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            return StringUtils.EMPTY;
        }
    }
}
