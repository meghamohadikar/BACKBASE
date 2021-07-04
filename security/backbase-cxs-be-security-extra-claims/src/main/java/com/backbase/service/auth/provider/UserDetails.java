package com.backbase.service.auth.provider;

public class UserDetails {

    private String username;
    private String name;
    private Boolean mobileActive;
    private String partyId;


    public UserDetails(String username, String name, Boolean mobileActive, String partyId) {
        this.username = username;
        this.name = name;
        this.mobileActive = mobileActive;
        this.partyId = partyId;
    }

    public Boolean getMobileActive() {
        return this.mobileActive;
    }

    public String getPartyId() {
        return this.partyId;
    }
}
