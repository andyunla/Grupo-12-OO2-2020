package com.sistema.application.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http.authorizeRequests()
         .anyRequest().authenticated()
         .and().formLogin();    // Configuración para que el login por defecto siga
         http.csrf().disable(); // Configuración para permitir llamadas AJAX sin tokens
    }
}
