package com.example.test.configs;

import com.example.test.constants.SecurityConstants;
import com.example.test.filters.JwtFilter;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;

import java.security.Key;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // enable cors
        http = http.cors().and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable();

        // set session management to stateless
        http = http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // set whitelist
        String[] WHITELIST = {
                "/logIn/**", "/register/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/webjars/**",
                "/swagger-ui/index.html", "/api-docs/**"
        };

        // set permissions on endpoints
        http = http.authorizeHttpRequests(authorizeRequest -> authorizeRequest
                .antMatchers(WHITELIST)
                .permitAll()
                .anyRequest().authenticated());

        // set unauthorized requests exception handler
        http = http.exceptionHandling(exceptions -> exceptions
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                .and());

        // add jwt token filter
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtFilter jwtTokenFilter() {
        return new JwtFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    Key jwtSecretKey() {
        return Keys.hmacShaKeyFor(SecurityConstants.JWT_SECRET.getBytes());
    }

    @Bean
    JwtParser jwtParser() {
        return Jwts.parserBuilder().setSigningKey(jwtSecretKey()).build();
    }
}