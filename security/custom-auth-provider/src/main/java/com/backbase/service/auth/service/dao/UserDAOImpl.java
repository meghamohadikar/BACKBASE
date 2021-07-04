package com.backbase.service.auth.service.dao;

import org.springframework.stereotype.Repository;

import static java.util.UUID.randomUUID;

/**
 * Emulation of a call to an external auth service or system
 */
@Repository
public class UserDAOImpl {

    public CustomExternalUser loadUserByUsernameAndPassword(final String username, final String password) {
        //just hardcoding the user
        CustomExternalUser user = new CustomExternalUser();
        user.setFirstName("Vanessa");
        user.setLastName("White");
        user.setGroup("business_manager");
        user.setUsername(username);
        user.setMobileActive(true);
        user.setPartyId(randomUUID().toString());
        return user;
    }
}