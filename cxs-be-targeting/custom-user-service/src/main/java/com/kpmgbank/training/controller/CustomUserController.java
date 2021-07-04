package com.kpmgbank.training.controller;

import com.kpmgbank.training.beans.CustomUser;
import com.kpmgbank.training.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class CustomUserController {

    @Autowired
    private CustomUserService userService;

    @RequestMapping("/get/{username}")
    public CustomUser getUserByUsername(@PathVariable("username") String username) {
        return userService.retreiveUser(username);
    }

}