package com.cplerings.core.infrastructure.jackson;

import com.cplerings.core.api.shared.NoData;
import com.cplerings.core.infrastructure.jackson.serializer.NoDataSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(NoData.class, new NoDataSerializer());
        objectMapper.registerModule(simpleModule);

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}
