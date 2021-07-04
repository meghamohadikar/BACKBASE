package com.backbase.service.auth.token;

import com.backbase.buildingblocks.jwt.external.ExternalJwtMapper;
import com.backbase.buildingblocks.jwt.external.token.ExternalJwtClaimsSet;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.service.auth.provider.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

// TODO 1 : Add annotation to make it a Spring Bean
@Component
// TODO 2 : Make this class implement ExternalJwtMapper
public class ExternalJWTMapperImpl implements ExternalJwtMapper {

    private static final Logger logger = LoggerFactory.getLogger(ExternalJWTMapperImpl.class);

    public Optional<ExternalJwtClaimsSet> claimSet(Authentication authentication, HttpServletRequest request) {

        if (authentication == null || !(authentication.getDetails() instanceof UserDetails)) {
            return Optional.empty();
        }

        UserDetails details = (UserDetails) authentication.getDetails();
        System.out.println("Response: details-" +details.getPartyId());

        // TODO 3 : Add the claim with name "mobileActive" and value from details.getMobileActive()
        Optional<ExternalJwtClaimsSet> response = Optional.of(new ExternalJwtClaimsSet.Builder()
                .addClaims(new ExternalJwtClaimsSet(Collections.singletonMap("partyId",details.getPartyId())))
                .addClaims(new ExternalJwtClaimsSet(Collections.singletonMap("mobileActive",details.getMobileActive())))
                .build());


        logger.debug("extraClaims: {}", response);
        System.out.println("Response:-" +response);

        return response;
    }

    public void updateAuthorities(Set<String> roles, Authentication authentication) {}

}