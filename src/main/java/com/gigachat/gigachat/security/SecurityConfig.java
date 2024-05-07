package com.gigachat.gigachat.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails ivan = User.builder()
                .username("Ivan")
                .password("{noop}bob123")
                .build();
                return new InMemoryUserDetailsManager(ivan);
    }

    /**
     * User must be authenticated.
     * @param httpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity.authorizeHttpRequests(
                configurer ->
                        configurer
                                .anyRequest().authenticated()
        )
                .formLogin(
                        form ->
                                form
                                        .loginPage("/showMyLogin")
                                        .loginProcessingUrl("/authenticateUser")
                                        .permitAll()
                )
                .logout(
                        logout ->
                                logout.permitAll()
                );
        return httpSecurity.build();
    }
}
