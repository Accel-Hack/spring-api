package com.accelhack.application.api.shared.security;

import com.accelhack.application.api.base.controller.base.ExternalController;
import com.accelhack.application.api.base.usecase.UserUsecase;
import com.accelhack.application.api.shared.config.AuthorizationConfiguration;
import com.accelhack.application.api.shared.filter.HttpServletRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
  private final AuthenticationConfiguration authenticationConfiguration;
  private final AuthorizationConfiguration authorizationConfiguration;
  private final UserDetailsService userDetailsService;
  private final UserUsecase userUsecase;
  private final ObjectMapper objectMapper;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        // .headers().frameOptions().sameOrigin()
        // .and()
        .csrf().disable().authenticationProvider(authProvider());

    routePaths(http);
    addFilters(http);

    return http.build();
  }

  private AuthenticationProvider authProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(authorizationConfiguration.getEncoder());
    return provider;
  }

  private AuthenticationManager authenticationManager() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * function to routing paths 1. allow external controller 2. others require authentication
   * 
   * @param http HttpSecurity
   * @throws Exception exception
   */
  private void routePaths(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((auth) -> auth
        // allow signIn for anonymous users
        .requestMatchers("/login").permitAll()
        // allow signIn for anonymous users
        .requestMatchers(ExternalController.CONTEXT_PATH + "/**").permitAll()
        // all other requests
        .anyRequest().authenticated());
  }

  /**
   * function to add filters 1. cache filter 2. validate login filter 3. validate jwt token filter
   * 
   * @param http HttpSecurity
   * @throws Exception exception
   */
  private void addFilters(HttpSecurity http) throws Exception {
    final Filter cache = new HttpServletRequestFilter();
    final Filter authentication =
        new JwtAuthenticationFilter(authenticationManager(), userUsecase, objectMapper);
    final Filter authorization =
        new JwtAuthorizationFilter(authenticationManager(), authorizationConfiguration);

    http.addFilterBefore(cache, UsernamePasswordAuthenticationFilter.class)
        .addFilter(authentication).addFilter(authorization).sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
}
