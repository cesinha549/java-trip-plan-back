package com.travelplanner.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("ALL")
@Configuration
public class SecurityConfig {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if ("dev".equals(activeProfile) || "test".equals(activeProfile)) {
            http
                    .authorizeHttpRequests(authz -> authz.anyRequest().permitAll())
                    .csrf(csrf -> csrf.disable())
                    .headers(headers -> headers.frameOptions().disable()); // for H2

        } else {
            http
                    .authorizeHttpRequests(authz -> authz
                            .requestMatchers("/public/**").permitAll()
                            .anyRequest().authenticated()
                    )
                    .csrf(csrf -> csrf.disable());
            // Add JWT auth config here when ready
        }

        return http.build();
    }
}
