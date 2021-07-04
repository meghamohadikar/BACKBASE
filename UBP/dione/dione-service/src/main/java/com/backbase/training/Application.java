package com.backbase.training;

import com.backbase.buildingblocks.backend.configuration.autoconfigure.BackbaseApplication;
import com.backbase.buildingblocks.jwt.internal.config.EnableInternalJwtConsumer;
import com.backbase.buildingblocks.registry.client.api.EnableRegistryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@BackbaseApplication
@EnableInternalJwtConsumer
@EnableRegistryClient
public class Application extends SpringBootServletInitializer {

    public static void main( String[] args) {
        SpringApplication.run(Application.class, args);
    }

}