package com.kpmgbank.training.service;

import com.kpmgbank.training.beans.CustomUser;
import com.kpmgbank.training.dao.CustomUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserService {

    @Autowired
    private CustomUserDAO userDAO;

    public CustomUser retreiveUser(String username) {
        List<CustomUser> users = userDAO.getUsers();
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(new CustomUser(username,"Erkin", "Pehlivan", 34));
    }
}