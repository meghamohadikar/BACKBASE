package com.backbase.training.example.generated.presentation.dummy.rest.spec.v1.example;

import java.io.Serializable;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//IMPORTANT: This class should be autogenerated from the RAML spec and included in your project as a dependency in the pom
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyPostResponseBody implements Serializable {

    @JsonProperty("id")
    private String id;

    /**
     *
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public DummyPostResponseBody withId(String id) {
        this.id = id;
        return this;
    }

}
