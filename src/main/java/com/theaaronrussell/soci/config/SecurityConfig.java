package com.theaaronrussell.soci.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);
        httpSecurity.requestCache(cache -> cache.requestCache(requestCache));
        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers("/signup").permitAll()
                .anyRequest().hasRole(Roles.ROLE_USER)
        );
        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

}
