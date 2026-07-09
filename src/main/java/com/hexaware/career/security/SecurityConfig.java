package com.hexaware.career.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hexaware.career.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final UserDetailsService userDetailsService;

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final PasswordConfig passwordConfig;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            UserDetailsService userDetailsService,
            AuthenticationEntryPoint authenticationEntryPoint,
            PasswordConfig passwordConfig) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.passwordConfig = passwordConfig;
    }

    @Bean
    AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);

        provider.setPasswordEncoder(
                passwordConfig.passwordEncoder());

        return provider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .cors(Customizer.withDefaults())

                .sessionManagement(session ->

                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))

                .exceptionHandling(exception ->

                        exception.authenticationEntryPoint(
                                authenticationEntryPoint))

                .authenticationProvider(authenticationProvider())

                .authorizeHttpRequests(auth -> auth

                        // Authentication APIs
                        .requestMatchers(
                                "/api/auth/**"
                        ).permitAll()

                        // Swagger
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Employer APIs (Only employers can post, update, or delete jobs)
                        .requestMatchers(
                                org.springframework.http.HttpMethod.POST, "/api/jobs"
                        ).hasRole("EMPLOYER")
                        .requestMatchers(
                                org.springframework.http.HttpMethod.PUT, "/api/jobs/**"
                        ).hasRole("EMPLOYER")
                        .requestMatchers(
                                org.springframework.http.HttpMethod.DELETE, "/api/jobs/**"
                        ).hasRole("EMPLOYER")

                        // Resume download (public access for browser inline viewing)
                        .requestMatchers(
                                "/api/resumes/download/**"
                        ).permitAll()

                        // Shared APIs (Applications, Resumes, and Jobs viewing)
                        .requestMatchers(
                                "/api/applications/**",
                                "/api/resumes/**"
                        ).hasAnyRole("JOB_SEEKER", "EMPLOYER")
                        .requestMatchers(
                                org.springframework.http.HttpMethod.GET, "/api/jobs", "/api/jobs/**"
                        ).hasAnyRole("JOB_SEEKER", "EMPLOYER")

                        // Shared Skills Read access
                        .requestMatchers(
                                org.springframework.http.HttpMethod.GET, "/api/skills/**"
                        ).hasAnyRole("JOB_SEEKER", "EMPLOYER")

                        // Job Seeker Skill Manipulation
                        .requestMatchers(
                                org.springframework.http.HttpMethod.POST, "/api/skills", "/api/skills/**"
                        ).hasRole("JOB_SEEKER")
                        .requestMatchers(
                                org.springframework.http.HttpMethod.PUT, "/api/skills", "/api/skills/**"
                        ).hasRole("JOB_SEEKER")
                        .requestMatchers(
                                org.springframework.http.HttpMethod.DELETE, "/api/skills", "/api/skills/**"
                        ).hasRole("JOB_SEEKER")

                        // Certification Download (public access)
                        .requestMatchers(
                                "/api/certifications/download/**"
                        ).permitAll()

                        // Certification GET access for both
                        .requestMatchers(
                                org.springframework.http.HttpMethod.GET, "/api/certifications/**"
                        ).hasAnyRole("JOB_SEEKER", "EMPLOYER")

                        // Certification Write access for Job Seeker
                        .requestMatchers(
                                org.springframework.http.HttpMethod.POST, "/api/certifications/**"
                        ).hasRole("JOB_SEEKER")
                        .requestMatchers(
                                org.springframework.http.HttpMethod.DELETE, "/api/certifications/**"
                        ).hasRole("JOB_SEEKER")

                        // Job Seeker Other APIs
                        .requestMatchers(
                                "/api/preferences/**"
                        ).hasRole("JOB_SEEKER")

                        // Remaining APIs
                        .anyRequest()
                        .authenticated());

        http.addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}