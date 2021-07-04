package com.backbase.training.example.api;

import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;

import com.backbase.signers.rest.spec.v1.PortfolioManagementAggregators.BadRequestException;
import com.backbase.signers.rest.spec.v1.PortfolioManagementAggregators.PortfolioManagementAggregatorsApi;
import com.backbase.signers.rest.spec.v1.PortfolioManagementAggregators.PortfolioManagementAggregatorsGetResponseBody;
import com.backbase.signers.rest.spec.v1.PortfolioManagementAggregators.RequestException;
import com.backbase.training.example.service.PortfolioManagementAggregatorService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PortfolioManagementAggregatorController  implements PortfolioManagementAggregatorsApi
{
    private final Logger logger = LoggerFactory.getLogger(PortfolioManagementAggregatorController.class);
    private PortfolioManagementAggregatorService service= new PortfolioManagementAggregatorService();
    private PortfolioManagementAggregatorsGetResponseBody portfoliomanagementAggregatorsGetResponseBody;

    @Override
    public PortfolioManagementAggregatorsGetResponseBody getPortfolioManagementAggregators(@RequestParam String company, @RequestParam String tiersNumber, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BadRequestException, RequestException {
        logger.info("getPersons controller");
        portfoliomanagementAggregatorsGetResponseBody=service.getSigners(company,tiersNumber);


        try {
            if(portfoliomanagementAggregatorsGetResponseBody==null)

                httpServletResponse.sendError(404,"not found");
        } catch (IOException e) {
            logger.info("getPersons controller"+e);;
        }
        return  portfoliomanagementAggregatorsGetResponseBody ;
    }
}


