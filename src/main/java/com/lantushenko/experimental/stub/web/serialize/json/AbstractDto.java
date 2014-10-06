package com.lantushenko.experimental.stub.web.serialize.json;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Base class for all DAO objects serialized to json
 */
@JsonSerialize
public abstract class AbstractDto {

    private Object id;

    @JsonProperty("id")
    public String getId() {
        return id.toString();
    }

    @JsonProperty("id")
    public void setId(Object value) {
        id = value;
    }
}
