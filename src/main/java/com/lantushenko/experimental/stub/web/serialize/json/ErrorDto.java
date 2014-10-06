package com.lantushenko.experimental.stub.web.serialize.json;

import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
public class ErrorDto {

    private final List<String> errorMessages;

    public ErrorDto(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public ErrorDto(String... errorMessages) {
        this.errorMessages = Arrays.asList(errorMessages);
    }

    @JsonProperty
    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
