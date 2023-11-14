package com.example.htpt.config;

import com.example.htpt.entity.enums.Role;
import com.example.htpt.service.CustomUserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.OnError;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final HttpSession session;
    private final CustomUserDetailService customUserDetailService;
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    try {
                        auth
                            .requestMatchers(
                                    "/auth/**",
                                    "/assets/**",
                                    "/public/**",
                                    "/home/**"
                            ).permitAll()
                            .requestMatchers("/customer/**").hasAnyAuthority(Role.CUSTOMER.name())
                            .requestMatchers("/employee/**").hasAnyAuthority(Role.EMPLOYEE.name(), Role.CUSTOMER.name())
                            .requestMatchers("/manager/**").hasAnyAuthority(Role.MANAGER.name(), Role.CUSTOMER.name())
                            .anyRequest().authenticated()
                            .and().formLogin()
                            .loginPage("/login.html")
                            .loginProcessingUrl("/auth/login")
                            .defaultSuccessUrl("/", true)
                            .successHandler((request, response, authentication) -> {
                                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                                session.setAttribute("username", userDetails.getUsername());
                                // Custom success URLs based on role
                                for (GrantedAuthority authority : userDetails.getAuthorities()) {
                                    if (authority.getAuthority().equals(Role.CUSTOMER.name())) {
                                        response.sendRedirect("/customer/home");
                                        return;
                                    } else if (authority.getAuthority().equals(Role.EMPLOYEE.name())) {
                                        response.sendRedirect("/employee/customers");
                                        return;
                                    } else if (authority.getAuthority().equals(Role.MANAGER.name())) {
                                        response.sendRedirect("/manager/home");
                                        return;
                                    }
                                }
                                // Default redirect if no matching role is found
                                response.sendRedirect("/");
                            })
                            .failureUrl("/login.html?error=true")
                            .failureHandler(authenticationFailureHandler())
                            .and()
                            .logout()
                            .logoutUrl("/auth/logout")
                            .deleteCookies("username")
                            .logoutSuccessHandler(logoutSuccessHandler());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                        }
                );
        return http.build();
    }
}
