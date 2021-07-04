package com.backbase.training.example.api;


import com.backbase.dione.rest.spec.v2.persons.PersonsGetResponseBody;
import com.backbase.training.Application;
import com.backbase.training.example.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.backbase.training.example.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/*@SpringBootTest(classes = Application.class, properties = {
        "buildingblocks.security.xss.autoconfig=true"})*/
@RunWith(SpringJUnit4ClassRunner.class)
public class PersonsApiControllerTest  {


    PersonsApiController personsApiController=new PersonsApiController();
    PersonsGetResponseBody personsGetResponseBody= new PersonsGetResponseBody();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getPersons()
    {
        personsGetResponseBody=personsApiController.getPersons("1478",null,null,null);
        String expected = "{\n" +
                "    \"id\": 1478,\n" +
                "    \"personNb\": 1,\n" +
                "    \"login\": \"machin\",\n" +
                "    \"firstName\": \"fran\",\n" +
                "    \"lastName\": \"truc\",\n" +
                "    \"companyCode\": \"FR201819700\",\n" +
                "    \"isAnonymous\": true,\n" +
                "    \"phones\": [\n" +
                "      {\n" +
                "        \"number\": \"+33688256482\",\n" +
                "        \"type\": \"string\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"roleAndSignatures\": [\n" +
                "      {\n" +
                "        \"signature\": \"signature\",\n" +
                "        \"role\": \"role\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }";

        String personsAsString=" " ;
        try {
            personsAsString = objectMapper.writeValueAsString(personsGetResponseBody);
            JSONAssert.assertEquals(expected, personsAsString, false);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}