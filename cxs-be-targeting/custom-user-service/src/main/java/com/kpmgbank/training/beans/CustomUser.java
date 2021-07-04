package com.kpmgbank.training.beans;

import java.io.Serializable;

public class CustomUser implements Serializable {

    private String username;
    private String firstname;
    private String lastname;
    private Integer age;

    public CustomUser() {
    }

    public CustomUser(String username, String firstname, String lastname, Integer age) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}