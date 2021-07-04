package com.backbase.training.example.api;

import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.dione.rest.spec.v2.persons.PersonsApi;
import com.backbase.dione.rest.spec.v2.persons.PersonsGetResponseBody;
import com.backbase.training.example.service.PersonService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PersonsApiController  implements PersonsApi
{
    private final Logger logger = LoggerFactory.getLogger(PersonsApiController.class);
    private PersonService person= new PersonService();
    private PersonsGetResponseBody personsGetResponseBody;

    @ResponseBody
    @Override
    public PersonsGetResponseBody getPersons(@RequestParam String personId, @RequestParam String ebankingLogin, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        logger.info("getPersons controller");
        personsGetResponseBody=person.getPersons(personId);


        try {
            if(personsGetResponseBody==null)

            httpServletResponse.sendError(404,"not found");
        } catch (IOException e) {
            logger.info("getPersons controller"+e);;
        }
        return  personsGetResponseBody ;
    }




}