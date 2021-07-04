package com.kpmgbank.training.dao;

import com.kpmgbank.training.beans.CustomUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomUserDAO {

    private static List<CustomUser> users;

    static {
        users = new ArrayList<>();
        users.add(new CustomUser("manager", "Jonathan", "Renders", 45));
        users.add(new CustomUser("user", "Erkin", "Pehlivan", 33));
        users.add(new CustomUser("admin", "Dragoslav", "Pesovic", 38));
    }

    public List<CustomUser> getUsers() {
        return users;
    }

}