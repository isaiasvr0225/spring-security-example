package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // First configuration type
    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeHttpRequests()
                  .requestMatchers("/v1/index2").permitAll()
                  .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .build();
    }

     */


    // Second configuration type using lambda expression and other configs
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests( auth -> {
                    auth.requestMatchers("/v1/index2").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin()
                  .successHandler(successHandler())
                  .permitAll()
                .and()
                .sessionManagement()
                  .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                  .invalidSessionUrl("/login")
                  .maximumSessions(1)
                  .expiredUrl("/login")
                  .sessionRegistry(sessionRegistryMethod())
                .and()
                .sessionFixation()
                  .migrateSession()
                .and()
                .build();
    }


    @Bean
    public SessionRegistry sessionRegistryMethod() {
        return new SessionRegistryImpl();
    }

    public AuthenticationSuccessHandler successHandler() {
        return (request, response, authentication) -> response.sendRedirect("/v1/index");
    }
}
