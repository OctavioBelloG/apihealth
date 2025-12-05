package com.example.configuration; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults; 

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.http.HttpMethod;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {


    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            
            .authorizeHttpRequests(auth -> auth
             
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/signout").permitAll()
              .requestMatchers(
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**"
            ).permitAll()
            
                .requestMatchers("/graphql/**", "/graphiql/**").permitAll() 
                .requestMatchers("/api/cognitive/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/patients/paged").hasRole("Doctor")
                .requestMatchers(HttpMethod.GET, "/api/v1/patients/with-appointments").hasRole("Doctor")
                .requestMatchers(HttpMethod.GET, "/api/v1/patients/search").hasRole("Doctor")
                .requestMatchers(HttpMethod.GET, "/api/v1/patients/{patientId}").hasRole("Doctor")
                .requestMatchers(HttpMethod.POST, "/api/v1/patients").hasAnyRole("Doctor", "Paciente") // O quien pueda crear
                .requestMatchers(HttpMethod.PUT, "/api/v1/patients/{patientId}").hasAnyRole("Doctor", "Paciente")
    
                .anyRequest().authenticated()
            )
           .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        )

        .authenticationProvider(authenticationProvider)

        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

        .formLogin(withDefaults()) 
        .httpBasic(withDefaults())

        .logout(logout -> logout.logoutUrl("/signout").permitAll());
        return http.build();
    }
}