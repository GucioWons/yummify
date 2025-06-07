package com.guciowons.yummify.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${spring.security.oauth2.resourceserver.jwt.issuers}")
    private List<String> issuers;

    private final Map<String, AuthenticationManager> authenticationManagers = new HashMap<>();

    private final JwtIssuerAuthenticationManagerResolver authenticationManagerResolver =
            new JwtIssuerAuthenticationManagerResolver(authenticationManagers::get);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        issuers.forEach(issuer -> addManager(authenticationManagers, issuer));

        http.authorizeHttpRequests(authz -> authz.anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2
                        .authenticationManagerResolver(authenticationManagerResolver)
                );
        return http.build();
    }

    private void addManager(Map<String, AuthenticationManager> authenticationManagers, String issuer) {
        JwtAuthenticationProvider authenticationProvider = new JwtAuthenticationProvider(JwtDecoders.fromOidcIssuerLocation(issuer));
        authenticationProvider.setJwtAuthenticationConverter(new CustomJwtAuthenticationConverter());
        authenticationManagers.put(issuer, authenticationProvider::authenticate);
    }
}
