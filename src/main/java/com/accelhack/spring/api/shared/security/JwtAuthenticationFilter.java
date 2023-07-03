package com.accelhack.spring.api.shared.security;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.accelhack.spring.api.application.usecase.UserUsecase;
import com.accelhack.spring.api.presentation.model.AuthenticationModel;
import com.accelhack.spring.api.presentation.model.AuthorizationModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final UserUsecase userUsecase;
  private final ObjectMapper objectMapper;

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException {
    final AuthenticationModel.Request request;
    try {
      request = objectMapper.readValue(req.getInputStream(), AuthenticationModel.Request.class);
    } catch (IOException e) {
      // unacceptable json request
      throw new BadCredentialsException("Invalid credentials", e);
    }
    return authenticationManager.authenticate(request.toToken());
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain, Authentication auth) throws IOException {
    final AuthorizationModel.Response response = userUsecase.login(auth.getName());
    final String json = objectMapper.writeValueAsString(response);

    res.setContentType(MediaType.APPLICATION_JSON_VALUE);
    res.getWriter().write(json);
    res.getWriter().flush();
  }
}
