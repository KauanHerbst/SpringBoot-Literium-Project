package com.herbst.literium.security;

import com.herbst.literium.security.exceptions.CustomBasicAuthenticationEntryPoint;
import com.herbst.literium.security.exceptions.CustomBearerTokenExcepetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private SecurityFilter securityFilter;
    @Autowired
    private CustomBasicAuthenticationEntryPoint customBasicAuthenticationEntryPoint;
    @Autowired
    private CustomBearerTokenExcepetion customBearerTokenExcepetion;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
            return httpSecurity
                    .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                            .requestMatchers(HttpMethod.POST, "/auth/valid").permitAll()
                            .requestMatchers(HttpMethod.POST, "/categories").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/categories").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/categories").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/categories").permitAll()
                            .requestMatchers(HttpMethod.GET, "/categories/{id}").permitAll()
                            .requestMatchers(HttpMethod.GET, "/categories/{categoryId}/books").permitAll()
                            .requestMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/books/").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/books").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/books**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/books/{id}").permitAll()
                            .requestMatchers(HttpMethod.GET, "/books/name/{name}").permitAll()
                            .requestMatchers(HttpMethod.POST, "/users").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/users").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/users").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/users").permitAll()
                            .requestMatchers(HttpMethod.GET, "/users/{id}").hasRole("USER")
                            .requestMatchers(HttpMethod.GET, "/favorites/{id}").permitAll()
                            .requestMatchers(HttpMethod.GET, "/orders/{id}").permitAll()
                                    .anyRequest().authenticated()
                            )
                    .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(customBasicAuthenticationEntryPoint))
                    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
