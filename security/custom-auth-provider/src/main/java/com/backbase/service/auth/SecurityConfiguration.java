package com.backbase.service.auth;

import com.backbase.buildingblocks.authentication.core.AuthSecurityConfiguration;
import com.backbase.service.auth.provider.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends AuthSecurityConfiguration {

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        //TODO 5 : Use authenticationManagerBuilder to add your Custom Authentication Provider in the list of authentication providers
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider);


    }
}