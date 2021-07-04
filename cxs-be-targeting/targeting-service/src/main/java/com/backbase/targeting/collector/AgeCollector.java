package com.backbase.targeting.collector;

import com.backbase.buildingblocks.jwt.internal.InternalJwtConsumer;
import com.backbase.buildingblocks.jwt.internal.token.InternalJwt;
import com.backbase.buildingblocks.jwt.internal.token.InternalJwtClaimsSet;
import com.backbase.cxp.targeting.api.Collector;
import com.backbase.cxp.targeting.contexts.AbstractContextCollector;
import com.backbase.cxp.targeting.contexts.definition.CollectorType;
import com.backbase.cxp.targeting.contexts.definition.OperatorPrimitive;
import com.backbase.cxp.targeting.contexts.definition.SelectorDefinition;
import com.backbase.cxp.targeting.contexts.definition.UniqueUserProfile;
import com.backbase.targeting.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author William Suane on 27/02/2018
 */
//TODO 4: Use the annotation that enable the targeting api to automatically discover this collector
//TODO 5: Use the following properties on the annotation id=user-details-collector, label=User Details
//TODO 6: This collector doesn't need to be executed on every request, use the correct type to gets data only when the session starts
//More information here: https://community.backbase.com/documentation/cxs/latest/understand-targeting-services
//And here: https://community.backbase.com/documentation/cxs/latest/targeting-services-background
public class AgeCollector extends AbstractContextCollector {
    private static final String SELECTOR_ID = "age-selector";
    private static final String SELECTOR_LABEL = "Age";

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${user-service-url}")
    private String serviceUrl;
    @Autowired
    private InternalJwtConsumer internalJwtConsumer;

    public AgeCollector() {
    }

    /**
     * This method prepares selectors for the collector
     *
     * @param portalName
     * @param requestParameters
     * @return List<SelectorDefinition>
     */
    @Override
    public List<SelectorDefinition> define(String portalName, Map<String, String> requestParameters) {
        //TODO 7: Create a SelectorDefinition and return it inside a list.
        return null;
    }

    /**
     * This method makes an external call and prepare uud
     *
     * @param requestMap
     * @param uup
     * @return UniqueUserProfile
     */
    @Override
    public UniqueUserProfile execute(Map<String, String> requestMap, UniqueUserProfile uup) {
        if (uup.isEmpty()) {
            try {
                // get logged in user information from InternalJwtConsumer
                String authorization = requestMap.get("headers.authorization");

                if (authorization != null) {
                    InternalJwt token = internalJwtConsumer.parseToken(authorization);
                    Optional<Object> internalJWTTokenForUsername = token.getClaimsSet().getClaim(InternalJwtClaimsSet.SUBJECT_CLAIM);

                    // username is sending as a PATH variable in querystring
                    String username = internalJWTTokenForUsername.get().toString();

                    // make restTemplate call to external service, URL should be something like : http://localhost:8088/users/get/admin where admin is logged in user
                    User user = restTemplate.getForEntity(serviceUrl + username, User.class).getBody();

                    // add user's age to uup
                    // TODO 8: Add to the UniqueUserProfile the selector age-selector and the user's age.

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return uup;
    }
}
