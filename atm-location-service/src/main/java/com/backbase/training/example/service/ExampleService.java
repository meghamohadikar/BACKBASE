package com.backbase.training.example.service;

import org.springframework.stereotype.Component;
import com.backbase.training.example.generated.presentation.dummy.rest.spec.v1.example.*;
/**
 * An example service handling the business logic of the service
 */
@Component
public class ExampleService {
    public DummyPostResponseBody create(DummyPostRequestBody requestBody) {
        return new DummyPostResponseBody().withId(requestBody.getId());
    }

}
