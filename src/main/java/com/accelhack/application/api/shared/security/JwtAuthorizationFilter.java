package com.accelhack.application.api.shared.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
  private static final String TOKEN_PREFIX = "Bearer ";
  @Value("${jwt.access-token.secret-key}")
  private String accessTokenSecretKey;

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
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
      final String user = JWT.require(Algorithm.HMAC512(accessTokenSecretKey.getBytes()))
        .build()
        .verify(StringUtils.substringAfter(token, TOKEN_PREFIX))
        .getSubject();

      if (user != null) {
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
      }
    }
    return null;
  }
}
