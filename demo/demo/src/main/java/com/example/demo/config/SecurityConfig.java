package com.example.demo.config;

import com.example.demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/login", "/auth/register").permitAll() // Allow login/register
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")// Admin-only endpoints
                        .requestMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN") // Allow both USER and ADMIN authorities// User-only endpoints
                        .anyRequest().authenticated()) // All other requests must be authenticated
                .httpBasic(Customizer.withDefaults()) // Using basic authentication
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session management (JWT-based)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // JWT filter
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(myUserDetailsService); // Use modified UserDetailsService
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private JwtFilter jwtFilter;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/auth/login", "/auth/register").permitAll() // Allow login/register
//                        .requestMatchers("/admin/**").hasRole("ADMIN") // Admin-only endpoints
//                        .requestMatchers("/user/**").hasRole("USER") // User-only endpoints
//                        .anyRequest().authenticated()) // All other requests must be authenticated
//                .httpBasic(Customizer.withDefaults()) // Using basic authentication
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session management (JWT-based)
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // JWT filter to be added before UsernamePasswordAuthenticationFilter
//                .build();
//    }
////  ****  @Bean  دي كان معمولها كومنت اصلا
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////
////        return http.csrf(customizer -> customizer.disable()).
////                authorizeHttpRequests(request -> request
////                        .requestMatchers("/auth/login", "/auth/register").permitAll()
////                        .anyRequest().authenticated()).
////                httpBasic(Customizer.withDefaults()).
////                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
////                .build();
////
////
////    }****
//
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//        provider.setUserDetailsService(userDetailsService);
//
//
//        return provider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//
//    }
//
//
//}
//
//
//
