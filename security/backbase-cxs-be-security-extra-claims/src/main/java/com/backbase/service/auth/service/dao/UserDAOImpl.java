package com.backbase.service.auth.service.dao;

import org.springframework.stereotype.Repository;

import static java.util.UUID.randomUUID;

@Repository
public class UserDAOImpl {

    public CustomExternalUser loadUserByUsernameAndPassword(final String username, final String password) {
        // Mocked DAO - just for testing
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
