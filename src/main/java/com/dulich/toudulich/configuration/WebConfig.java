package com.dulich.toudulich.configuration;

import com.dulich.toudulich.Model.RoleModel;
import com.dulich.toudulich.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebConfig {
    private final JwtTokenFilter jwtTokenFilter ;

    @Value("${api.prefix}")
    private String apiPrefix ;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> {
                    request
                            // Public endpoints
                            .requestMatchers(
                                    String.format("%s/users/register", apiPrefix),
                                    String.format("%s/users/login", apiPrefix)
                            ).permitAll()

                            // bookings endpoints
                            .requestMatchers(HttpMethod.PUT, String.format("%s/bookings/**", apiPrefix)).hasRole(RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.POST, String.format("%s/bookings/**", apiPrefix)).hasRole(RoleModel.USER)
                            .requestMatchers(HttpMethod.DELETE, String.format("%s/bookings/**", apiPrefix)).hasRole(RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.GET, String.format("%s/bookings/**", apiPrefix)).hasAnyRole(RoleModel.USER, RoleModel.ADMIN)

                            // tours endpoints
                            .requestMatchers(HttpMethod.PUT, String.format("%s/tours/**", apiPrefix)).hasRole(RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.POST, String.format("%s/tours/**", apiPrefix)).hasRole(RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.DELETE, String.format("%s/tours/**", apiPrefix)).hasRole( RoleModel.ADMIN)
                            .requestMatchers(HttpMethod.GET, String.format("%s/tours/**", apiPrefix)).hasAnyRole(RoleModel.ADMIN, RoleModel.USER)

                            // Any other request must be authenticated
                            .anyRequest().authenticated();
                });

        return httpSecurity.build();
    }
}
