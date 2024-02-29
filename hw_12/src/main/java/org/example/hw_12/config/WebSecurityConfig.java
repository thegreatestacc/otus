package org.example.hw_12.config;

import org.example.hw_12.models.roles.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .authorizeHttpRequests(
                        requests -> requests
                                .antMatchers(HttpMethod.GET, "home/login").permitAll()
                                .antMatchers(HttpMethod.GET, "home/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                                .antMatchers(HttpMethod.POST, "home/**").hasRole(Role.ADMIN.name())
                                .antMatchers(HttpMethod.DELETE, "home/**").hasRole(Role.ADMIN.name())
                )
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("pwd"))
                        .roles(Role.ADMIN.name())
                        .build(),

                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("usr"))
                        .roles(Role.USER.name())
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}

