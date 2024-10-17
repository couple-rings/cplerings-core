package com.cplerings.core.infrastructure.jackson.serializer;

import com.cplerings.core.api.shared.NoData;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class NoDataSerializer extends JsonSerializer<NoData> {

    @Override
    public void serialize(NoData value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNull();
    }
}
