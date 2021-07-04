package com.backbase.service.auth;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class SecureApplicationTest {

    @Autowired
    private ApplicationContext context;

    private AuthenticationManager authenticationManager;

    @Before
    public void init() {
        this.authenticationManager = this.context.getBean(AuthenticationManager.class);
    }

    @Test
    public void shouldBeAuthenticatedAsAdmin() {
        assertTrue(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("admin", "admin")).isAuthenticated());
    }

    @Test(expected = BadCredentialsException.class)
    public void shouldThrowBadCredentialsException() {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("admin", "wrong password"));
    }

    @After
    public void close() {
        SecurityContextHolder.clearContext();
    }
}