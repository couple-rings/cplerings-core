package com.cplerings.core.api.shared;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class NoDataSerializer extends JsonSerializer<NoData> {

    @Override
    public void serialize(NoData value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNull();
    }
}
