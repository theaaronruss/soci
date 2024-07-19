package com.theaaronrussell.soci.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("Setting up custom SecurityFilterChain");
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        httpSecurity.requestCache(cache -> cache.requestCache(requestCache));
        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers("/signup").permitAll()
                .anyRequest().hasRole(Roles.ROLE_USER)
        );
        httpSecurity.formLogin(login -> login.loginPage("/login").permitAll());
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Setting up custom PasswordEncoder");
        return new BCryptPasswordEncoder();
    }

}
