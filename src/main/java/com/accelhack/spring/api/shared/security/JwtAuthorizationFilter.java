package com.accelhack.spring.api.shared.security;

import com.accelhack.spring.api.shared.config.AuthorizationConfiguration;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
  private static final String TOKEN_PREFIX = "Bearer ";
  private final AuthorizationConfiguration config;

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
      AuthorizationConfiguration config) {
    super(authenticationManager);
    this.config = config;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {
    final String header = req.getHeader(HttpHeaders.AUTHORIZATION);

    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(req, res);
      return;
    }

    final UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(req, res);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    final String token = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (token != null) {
      final String user = JWT.require(Algorithm.HMAC512(config.getAccessTokenSecret().getBytes()))
          .build().verify(StringUtils.replace(token, TOKEN_PREFIX, "")).getSubject();

      if (user != null) {
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
      }
    }
    return null;
  }
}
