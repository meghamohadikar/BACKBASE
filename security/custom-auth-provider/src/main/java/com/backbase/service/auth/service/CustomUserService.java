package com.backbase.service.auth.service;

import com.backbase.service.auth.service.dao.CustomExternalUser;
import com.backbase.service.auth.service.dao.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * [Class description.  The first sentence should be a meaningful summary of the class since it
 * will be displayed as the class summary on the Javadoc package page.]
 *
 * @author luca
 * @version 1.0
 * @since 14/12/2017
 * <p>
 * Imagination is more important than knowledge. @AE
 */

@Component
public class CustomUserService {

    @Autowired
    private UserDAOImpl userDao;

    public CustomExternalUser retrieveUser(String username, String password) throws UsernameNotFoundException {
        return userDao.loadUserByUsernameAndPassword(username, password);
    }
}