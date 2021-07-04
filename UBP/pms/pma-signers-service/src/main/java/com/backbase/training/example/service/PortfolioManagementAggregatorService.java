package com.backbase.training.example.service;

import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.signers.rest.spec.v1.PortfolioManagementAggregators.PortfolioManagementAggregatorsGetResponseBody;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PortfolioManagementAggregatorService {

    private final Logger logger = LoggerFactory.getLogger(PortfolioManagementAggregatorService.class);
    private PortfolioManagementAggregatorsGetResponseBody signersdata =new  PortfolioManagementAggregatorsGetResponseBody();
    private ObjectMapper objectMapper = new ObjectMapper();

    public PortfolioManagementAggregatorsGetResponseBody getSigners( String company,String tiersNumber) {
        List<PortfolioManagementAggregatorsGetResponseBody> signersGetResponseBody;
        logger.info("getPersons services");
        String json = null;
        try {
            json = executeRequest();

            signersGetResponseBody = objectMapper.readValue(json, new TypeReference<List<PortfolioManagementAggregatorsGetResponseBody>>(){});


            signersdata = signersGetResponseBody.stream().filter( p1 -> (p1.getCompany().equalsIgnoreCase(company) && p1.getTiersNumber().equalsIgnoreCase(tiersNumber)) ).findAny().orElse(null);
            System.out.println("json=="+ signersdata.getPersonBirthdate());


        } catch (IOException e) {
            logger.info("error"+e);
        }
        return signersdata;
    }

    public String executeRequest() throws IOException
    {
        logger.info("Looks like the server is down, getting the JSON from the file");
        return new String(Files.readAllBytes(Paths.get("../data/sample_pma_signers.json")));
    }
}