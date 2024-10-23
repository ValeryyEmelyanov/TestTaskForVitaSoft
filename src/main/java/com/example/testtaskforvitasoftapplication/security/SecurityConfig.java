package com.example.testtaskforvitasoftapplication.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/user/**").hasAnyRole("USER")
                .antMatchers("/api/operator/**").hasRole("OPERATOR")
                .antMatchers("/api/admin/**").hasRole("ADMINISTRATOR")
                .anyRequest().denyAll() // Все остальные запросы запрещены
                .and()
                .formLogin();
    }
}
