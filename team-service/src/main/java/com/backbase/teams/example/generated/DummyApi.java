package com.backbase.teams.example.generated;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.backbase.teams.example.generated.presentation.dummy.rest.spec.v1.example.*;

//IMPORTANT: This class should be autogenerated from the RAML spec and included in your project as a dependency in the pom
@RequestMapping({"/v1/example"})
@RestController
public interface DummyApi {

    @RequestMapping(
            method = {RequestMethod.POST},
            value = {"/create"}
    )
    @ResponseStatus(HttpStatus.CREATED)
    DummyPostResponseBody create(
            @RequestBody @Valid DummyPostRequestBody requestBody,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse);

}
