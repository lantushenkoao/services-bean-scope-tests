package com.lantushenko.experimental.stub.services;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

public interface JsonSerializer {

    <T> String serialize(T objectsToSerialize) throws JsonGenerationException, JsonMappingException, IOException;

    <T> T deserialize(String jsonToDeserialize, Class<T> objectsClass) throws JsonParseException, JsonMappingException,
            IOException;

}
