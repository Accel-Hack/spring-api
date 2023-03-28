package com.accelhack.application.api.shared.security;

import com.accelhack.application.api.shared.entity.User;
import com.accelhack.application.api.shared.service.JwtUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final JwtUserDetailsService userDetailsService;
  private final ObjectMapper objectMapper;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
    try {
      final User.Authentication user = objectMapper.readValue(req.getInputStream(), User.Authentication.class);
      return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), Collections.emptyList()));
    } catch (IOException e) {
      throw new BadCredentialsException("Invalid credentials", e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
    final User.Token issueToken = userDetailsService.issueToken(auth.getName());
    final String json = objectMapper.writeValueAsString(issueToken);

    res.setContentType(MediaType.APPLICATION_JSON_VALUE);
    res.getWriter().write(json);
    res.getWriter().flush();
  }
}
