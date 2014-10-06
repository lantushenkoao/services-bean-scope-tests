package com.lantushenko.experimental.stub.services.impl;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.lantushenko.experimental.stub.services.JsonSerializer;

@Component
public class JsonSerializerImpl implements JsonSerializer {

    @Override
    public <T> String serialize(T objectsToSerialize) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        StringWriter sw = new StringWriter();
        mapper.writeValue(sw, objectsToSerialize);
        return sw.toString();
    }

    @Override
    public <T> T deserialize(String jsonToDeserialize, Class<T> objectsClass) throws JsonParseException,
            JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringReader sr = new StringReader(jsonToDeserialize);

        return mapper.readValue(sr, objectsClass);
    }
}
