package com.backbase.training.example.service;

import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.dione.rest.spec.v2.persons.PersonsGetResponseBody;
import com.backbase.training.example.api.PersonsApiController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PersonService {

    private final Logger logger = LoggerFactory.getLogger(PersonsApiController.class);
    private PersonsGetResponseBody persondata =new  PersonsGetResponseBody();
    private ObjectMapper objectMapper = new ObjectMapper();

    public PersonsGetResponseBody getPersons( String personId) {
        List<PersonsGetResponseBody> personsGetResponseBody;
        logger.info("getPersons services");
        String json = null;
        try {
            json = executeRequest();
            personsGetResponseBody = objectMapper.readValue(json, new TypeReference<List<PersonsGetResponseBody>>(){});
         persondata = personsGetResponseBody.stream().filter(p -> p.getId() == Integer.parseInt(personId)).findAny().orElse(null);
        } catch (IOException e) {
            logger.info("error"+e);
        }
         return persondata;
    }

    public String executeRequest() throws IOException
    {
        logger.info("Looks like the server is down, getting the JSON from the file");
        return new String(Files.readAllBytes(Paths.get("../data/personJson.json")));
    }
}