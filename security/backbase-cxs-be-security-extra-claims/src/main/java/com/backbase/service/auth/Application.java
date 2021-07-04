package com.backbase.service.auth;

import com.backbase.buildingblocks.authentication.core.EnableDefaultAuthEndpoint;
import com.backbase.buildingblocks.registry.client.api.EnableRegistryClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableRegistryClient
@EnableDefaultAuthEndpoint
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}