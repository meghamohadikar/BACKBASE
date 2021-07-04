package com.backbase.service.auth.service;

import com.backbase.service.auth.service.dao.CustomExternalUser;
import com.backbase.service.auth.service.dao.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserService {

    @Autowired
    private UserDAOImpl userDao;

    public CustomExternalUser retrieveUser(String username, String password) throws UsernameNotFoundException {
        return userDao.loadUserByUsernameAndPassword(username, password);
    }
}
