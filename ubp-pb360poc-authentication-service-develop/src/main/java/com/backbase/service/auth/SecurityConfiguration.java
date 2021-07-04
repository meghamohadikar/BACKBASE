package com.backbase.service.auth;

import com.backbase.buildingblocks.authentication.core.AuthEndpoints;
import com.backbase.buildingblocks.authentication.core.AuthSecurityConfiguration;
import com.backbase.buildingblocks.authentication.core.filter.AuthInternalJwtConsumerFilter;
import com.backbase.buildingblocks.logging.api.Logger;
import com.backbase.buildingblocks.logging.api.LoggerFactory;
import com.backbase.buildingblocks.security.csrf.CsrfWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.kerberos.authentication.KerberosServiceAuthenticationProvider;
import org.springframework.security.kerberos.authentication.sun.SunJaasKerberosTicketValidator;
import org.springframework.security.kerberos.client.config.SunJaasKrb5LoginConfig;
import org.springframework.security.kerberos.client.ldap.KerberosLdapContextSource;
import org.springframework.security.kerberos.web.authentication.SpnegoAuthenticationProcessingFilter;
import org.springframework.security.kerberos.web.authentication.SpnegoEntryPoint;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.LdapUserDetailsService;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends AuthSecurityConfiguration {

    Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Value("${kerberos.adDomain}")
    private String adDomain;

    @Value("${kerberos.adServer}")
    private String adServer;

    @Value("${kerberos.servicePrincipal}")
    private String servicePrincipal;

    @Value("${kerberos.classpathResources}")
    private boolean classpathResources;

    @Value("${kerberos.keytabLocation}")
    private String keytabLocation;

    @Value("${kerberos.krb5Ini}")
    private String krb5IniLocation;

    @Value("${kerberos.ldapSearchBase}")
    private String ldapSearchBase;

    @Value("${kerberos.ldapSearchFilter}")
    private String ldapSearchFilter;

    @Value("${kerberos.debugMode}")
    private boolean debugMode;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

            http.exceptionHandling().authenticationEntryPoint(spnegoEntryPoint());
            http.addFilterBefore(spnegoAuthenticationProcessingFilter(authenticationManager()), BasicAuthenticationFilter.class);
            http.csrf().ignoringAntMatchers(AuthEndpoints.LOGIN.getPath() + "*/**");
            http.csrf().ignoringAntMatchers(AuthEndpoints.LOGOUT.getPath() + "*/**");
            http.csrf().ignoringAntMatchers(AuthEndpoints.TOKEN_CONVERTER.getPath() + "*/**");
        }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER", "group_user(USER)");
 //          auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN", "ACTUATOR", "group_admin(ADMIN)");
//        auth.inMemoryAuthentication().withUser("manager").password("manager").roles("USER", "group_manager(MANAGER)");


        auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());

        auth.authenticationProvider(kerberosServiceAuthenticationProvider());
    }

    @Bean
    public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {

        return new ActiveDirectoryLdapAuthenticationProvider(adDomain, adServer);
    }

    @Bean
    public SpnegoEntryPoint spnegoEntryPoint() {
        return new SpnegoEntryPoint("/");
    }

    @Bean
    public SpnegoAuthenticationProcessingFilter spnegoAuthenticationProcessingFilter(AuthenticationManager auth) {
        SpnegoAuthenticationProcessingFilter filter = new SpnegoAuthenticationProcessingFilter();
        filter.setAuthenticationManager(auth);
        return filter;
    }

    @Bean
    public KerberosServiceAuthenticationProvider kerberosServiceAuthenticationProvider() throws Exception {

        KerberosServiceAuthenticationProvider provider = new KerberosServiceAuthenticationProvider();
        provider.setTicketValidator(sunJaasKerberosTicketValidator());

        provider.setUserDetailsService(ldapUserDetailsService());

        return provider;
    }

    @Bean
    public SunJaasKerberosTicketValidator sunJaasKerberosTicketValidator() {

        System.setProperty("java.security.krb5.conf", krb5IniLocation);
        SunJaasKerberosTicketValidator ticketValidator = new SunJaasKerberosTicketValidator();
        ticketValidator.setServicePrincipal(servicePrincipal);
        ticketValidator.setKeyTabLocation(classpathResources ? new ClassPathResource(keytabLocation) : new FileSystemResource(keytabLocation));
        ticketValidator.setDebug(debugMode);
        return ticketValidator;
    }

    @Bean
    public KerberosLdapContextSource kerberosLdapContextSource() throws Exception {
        KerberosLdapContextSource contextSource = new KerberosLdapContextSource(adServer);
        contextSource.setLoginConfig(loginConfig());
        return contextSource;
    }

    public SunJaasKrb5LoginConfig loginConfig() throws Exception {
        SunJaasKrb5LoginConfig loginConfig = new SunJaasKrb5LoginConfig();
        loginConfig.setKeyTabLocation(classpathResources ? new ClassPathResource(keytabLocation) : new FileSystemResource(keytabLocation));
        loginConfig.setServicePrincipal(servicePrincipal);
        loginConfig.setDebug(debugMode);
        loginConfig.setIsInitiator(true);
        loginConfig.afterPropertiesSet();
        return loginConfig;
    }

    @Bean
    public LdapUserDetailsService ldapUserDetailsService() throws Exception {

        FilterBasedLdapUserSearch userSearch = new FilterBasedLdapUserSearch(ldapSearchBase, ldapSearchFilter, kerberosLdapContextSource());
        LdapUserDetailsService service = new LdapUserDetailsService(userSearch, new UbpActiveDirectoryLdapAuthoritiesPopulator());
        service.setUserDetailsMapper(new LdapUserDetailsMapper());
        return service;
    }
}