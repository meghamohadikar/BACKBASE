package com.backbase.notes.example.service;

import org.springframework.stereotype.Component;
import com.backbase.notes.example.generated.presentation.dummy.rest.spec.v1.example.*;
/**
 * An example service handling the business logic of the service
 */
@Component
public class ExampleService {
    public DummyPostResponseBody create(DummyPostRequestBody requestBody) {
        return new DummyPostResponseBody().withId(requestBody.getId());
    }

}
