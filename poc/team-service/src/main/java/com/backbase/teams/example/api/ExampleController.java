package com.backbase.teams.example.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import com.backbase.teams.example.generated.DummyApi;
import com.backbase.teams.example.generated.presentation.dummy.rest.spec.v1.example.*;
import com.backbase.teams.example.service.ExampleService;

/**
 * Rest controller for the service, defines all methods listed in the RAML
 */
@RestController
public class ExampleController implements DummyApi {

    @Autowired
    public ExampleService exampleService;

    @Override
    public DummyPostResponseBody create(
            @RequestBody @Valid DummyPostRequestBody requestBody,
            final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse) {
        return exampleService.create(requestBody);
    }

}
