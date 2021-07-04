package com.backbase.training.example.api;
import com.backbase.signers.rest.spec.v1.PortfolioManagementAggregators.PortfolioManagementAggregatorsGetResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*@SpringBootTest(classes = Application.class, properties = {
        "buildingblocks.security.xss.autoconfig=true"})*/
@RunWith(SpringJUnit4ClassRunner.class)
public class PortfolioManagementAggregatorControllerTest {


    PortfolioManagementAggregatorController portfolioManagementAggregatorController=new PortfolioManagementAggregatorController();
    PortfolioManagementAggregatorsGetResponseBody signersGetResponseBody= new PortfolioManagementAggregatorsGetResponseBody();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getPortfolioManagementAggregators()
    {
        signersGetResponseBody=portfolioManagementAggregatorController.getPortfolioManagementAggregators("001","6045960",null,null);
        String expected = "{\n" +
                "    \"company\": \"001\",\n" +
                "    \"tiersNumber\": \"6045960\",\n" +
                "    \"personName\": \"pierre\",\n" +
                "    \"personSurname\": \"martin\",\n" +
                "    \"personBirthdate\": \"2017-01-02 05:30:00\",\n" + // 1483367063000 2017-01-02T14:24:23
                "    \"portfolioCode\": \"PTF\",\n" +
                "    \"personId\": 123,\n" +
                "    \"hasSignaturePower\": true,\n" +
                "    \"homeCountry\": \"FR\",\n" +
                "    \"personType\": \"VIP\",\n" +
                "    \"personRoles\": \"RM\",\n" +
                "    \"personSignature\": \"SIGNED\",\n" +
                "    \"isRepresentative\": true,\n" +
                "    \"nameMifidCat\": \"MIFID1\",\n" +
                "    \"knowledge\": 20.2,\n" +
                "    \"experience\": 30.3\n" +
                "  }";

        String personsAsString=" " ;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            objectMapper.setDateFormat(df);
            personsAsString = objectMapper.writeValueAsString(signersGetResponseBody);
            System.out.println("signersGetResponseBody=="+signersGetResponseBody.getPersonBirthdate());
            System.out.println("personsAsString"+personsAsString);
            JSONAssert.assertEquals(expected, personsAsString, false);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}